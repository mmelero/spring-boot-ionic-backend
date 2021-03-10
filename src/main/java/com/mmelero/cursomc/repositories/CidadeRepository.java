package com.mmelero.cursomc.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmelero.cursomc.domain.Cidade;

/*@Repository
public interface CidadeRepository  extends JpaRepository<Cidade, Long>{

	@Transactional
	@Query("Select obj from Cidade obj where obj.estado.id = :estado_Id order by obj.nome")
	public List<Cidade>findCidades(@Param("estadoId") Long estado_id );
	 
}*/
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	 
	  @Transactional
	  public List<Cidade> findCidadesByEstadoId(Long id);
	 
} 
