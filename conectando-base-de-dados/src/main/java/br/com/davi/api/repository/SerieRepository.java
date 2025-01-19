package br.com.davi.api.repository;

import br.com.davi.api.model.Episodio;
import br.com.davi.api.model.Genero;
import br.com.davi.api.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
   Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

   List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, Double avaliacao);

   List<Serie> findTop5ByOrderByAvaliacaoDesc();

   List<Serie> findByGenero(Genero genero);

   List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);

   //@Query(value = "select * from series where series.total_temporadas < ? and avaliacao > ?", nativeQuery = true)
   //Query nativa
   @Query("select s from Serie s where s.totalTemporadas < :totalTemporadas and s.avaliacao > :avaliacao")
   //jpql -> usando as classes e atributos
   List<Serie> seriesPorTemporadaEAvaliacao(int totalTemporadas, double avaliacao);

   @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
   List<Episodio> episodiosPorTrecho(String trechoEpisodio);

   @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
   List<Episodio> topEpisodiosPorSerie(Serie serie);

   @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) > :anoLancamento")
   List<Episodio> episodiosPorSeriePorAno(Serie serie, int anoLancamento);
}
