package server;

public interface ISearcher<T> {
	public Solution<T> search(ISearchable<T> searchable);
	public int getNumOfEvaluatedNodes();
}
