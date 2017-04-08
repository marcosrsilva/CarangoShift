package br.com.marcosroberto.carango.fragments;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.List;
import br.com.marcosroberto.carango.R;
import br.com.marcosroberto.carango.adapter.CarroListaAdapter;
import br.com.marcosroberto.carango.api.CarroAPI;
import br.com.marcosroberto.carango.listener.OnClickListener;
import br.com.marcosroberto.carango.model.Carro;
import br.com.marcosroberto.carango.utils.ConstantesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends Fragment
{
    private RecyclerView recyclerView;
    private CarroListaAdapter carroListaAdapter;
    private String tipoCarros;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(getArguments() !=null)
        {
            this.tipoCarros = getArguments().getString(ConstantesUtils.CHAVE_TIPO);
        }
    }

    public CarrosFragment()
    {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_carros, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        carregaDados();
    }

    //Busca as informações nos servidores
    private void carregaDados()
    {
        Retrofit retrofit= new Retrofit.Builder().baseUrl(ConstantesUtils.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        CarroAPI api = retrofit.create(CarroAPI.class);
        Call<List<Carro>> call = api.buscarCarros(tipoCarros);
        //Chamada para não travar o celular
        call.enqueue(new Callback<List<Carro>>()
        {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response)
            {
                carroListaAdapter = new CarroListaAdapter(getContext(), response.body(),cliqueNaLista);
                recyclerView.setAdapter(carroListaAdapter);
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t)
            {
                Toast.makeText(getContext(), "Ops! Deu ruim! Tente novamente mais tarde!",Toast.LENGTH_LONG).show();
            }
        });
    }
    //Criando o clique da lista
    private OnClickListener cliqueNaLista = new OnClickListener()
    {
        @Override
        public void onClick(View view, int position)
        {
            Carro carroClicado= carroListaAdapter.getItem(position);
            Toast.makeText(getContext(),carroClicado.getNome(),Toast.LENGTH_SHORT).show();
        }
    };
}