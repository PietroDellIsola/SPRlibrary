package com.ap.SPRlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;/*
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;*/

import com.ap.SPRlibrary.entity.Loan;

@Repository("loanRepository")
public interface LoanRepository extends JpaRepository<Loan, Integer> {
	
	/*
	@Query(value = "SELECT * FROM videogioco WHERE num_copiepc < 1 AND num_copie_ps4 < 1 AND num_copie_xbox < 1", nativeQuery = true)
	public List<Videogioco> getVideogiochiConCopieTerminate();
	
	@Transactional
	@Modifying /*@Modifying is for telling spring-data-jpa that this query is an update operation and it requires executeUpdate() not executeQuery().
	@Query(value = " UPDATE Videogioco SET trama = :trama , tipo = :tipo , data_uscita = :dataUscita , num_copiepc = :numCopiePC, num_copie_ps4 = :numCopiePs4, "
			+ " num_copie_xbox = :numCopieXbox, casa_produttrice = :casaProduttrice WHERE titolo = :titolo ", nativeQuery = true)
	public int updateVideogioco(@Param("titolo")String titolo, @Param("trama")String trama, @Param("tipo")String tipo, @Param("dataUscita")Calendar dataUscita, 
			@Param("numCopiePC")Integer numCopiePC, @Param("numCopieXbox")Integer numCopieXbox, @Param("numCopiePs4")Integer numCopiePs4, 
			@Param("casaProduttrice")String casaProduttrice); /*il metodo updateVideogioco dovrebbe ritornare il numero di colonne aggioirnate(CONTROLLA)*/
	
	/*Esempio:
	 * @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
     * public List<Person> find(@Param("lastName") String lastName);
	*/

}
