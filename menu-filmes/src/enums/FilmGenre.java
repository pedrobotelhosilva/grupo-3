package enums;

public enum FilmGenre
{
  ACTION("Ação"),
  COMEDY("Comédia"),
  DRAMA("Drama"),
  HORROR("Terror"),
  ROMANCE("Romance"),
  SCIENCE_FICTION("Ficção Científica"),
  SUSPENCE("Suspence"),
  ANIMATION("Animação"),
  DOCUMENTARY("Documentário");

  private final String genre;

  FilmGenre(String gen)
  {
    this.genre = gen;
  }

  public String getGenre()
  {
    return (this.genre);
  }
}
