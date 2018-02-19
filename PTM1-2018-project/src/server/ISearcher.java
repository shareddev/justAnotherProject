package server;

public interface ISearcher<GAME> {
	public Solution<GAME> search(ISearchable<GAME> searchable);
	public int getNumOfEvaluatedNodes();
}
