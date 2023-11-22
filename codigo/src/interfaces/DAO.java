package src.interfaces;

import java.util.List;

import src.exceptions.ExcecaoCadastrarVeiculoExistente;
import src.exceptions.ExcecaoClientejaExistente;

public interface DAO<T, K> {
	public T get(K chave);
	public void add(T p) throws ExcecaoCadastrarVeiculoExistente, ExcecaoClientejaExistente;
	public void update(T p, K key);
	public void delete(T p);
	public List<T> getAll();
}