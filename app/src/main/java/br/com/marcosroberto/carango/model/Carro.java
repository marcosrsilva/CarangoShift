package br.com.marcosroberto.carango.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by logonrm on 08/04/2017.
 */
public class Carro
{
    @SerializedName(value = "nome")
    private String nome;
    @SerializedName(value = "desc")
    private String descricao;
    @SerializedName(value = "foto")
    private String url_Imagem;

    public Carro() {

    }
    public Carro(String nome, String descricao, String url_Imagem)
    {
        this.nome = nome;
        this.descricao = descricao;
        this.url_Imagem = url_Imagem;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getUrl_Imagem()
    {
        return url_Imagem;
    }

    public void setUrl_Imagem(String url_Imagem)
    {
        this.url_Imagem = url_Imagem;
    }
}