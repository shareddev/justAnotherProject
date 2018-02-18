package server;

public interface ISearcher<GAME> {
	public ISolution<GAME> search(ISearchable<GAME> searchable);
}
