package com.mmelero.cursomc.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mmelero.cursomc.domain.Cidade;
import com.mmelero.cursomc.domain.Cliente;
import com.mmelero.cursomc.domain.Endereco;
import com.mmelero.cursomc.domain.enuns.Perfil;
import com.mmelero.cursomc.domain.enuns.TipoCliente;
import com.mmelero.cursomc.dto.ClienteDTO;
import com.mmelero.cursomc.dto.ClienteNewDTO;
import com.mmelero.cursomc.repositories.ClienteRepository;
import com.mmelero.cursomc.repositories.EnderecoRepository;
import com.mmelero.cursomc.security.UserSS;
import com.mmelero.cursomc.services.exceptions.AutorizationException;
import com.mmelero.cursomc.services.exceptions.DataIntegrityException;
import com.mmelero.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Service;
	
	public Cliente find(Long id) {
		
		//validando o usuario para verficar o perfil
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Perfil.ADMIN) &&!id.equals(user.getId())) {
			throw new AutorizationException("Acesso Negado!!");
		}
		
		Optional<Cliente> obj = repo.findById((Long)id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há Pedidos relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		//Atribuir o endereço ao cliente
		cli.getEnderecos().add(end);
		//Atribuir os telefones ao cliente
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	//metodo especifico para efetuar upload da foto especifico do cliente
	public URI uploadProfilePicture(MultipartFile multiparteFile) {
		return s3Service.uploadFile(multiparteFile);
	}
}
