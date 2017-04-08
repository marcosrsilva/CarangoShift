package br.com.marcosroberto.carango.api;

import java.util.List;
import br.com.marcosroberto.carango.model.Carro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CarroAPI
{
    @GET("/carros/tipo/{tipo}.json")
    Call<List<Carro>> buscarCarros(@Path("tipo") String tipo);
}