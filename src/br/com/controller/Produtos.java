package br.com.controller;

/**
 * Created by weslley on 10/05/2016.
 */
public class Produtos {
    private int codigo_produto;
    private int codigo_barras;
    private String nome_produto;
    private String marca_produto;
    private Double preco_produto;
    private int quantidade_produto;
    private String fornecedor_produto;
    private String Descricao_produto;


    public int getCodigo_produto() {
        return codigo_produto;
    }

    public void setCodigo_produto(int codigo_produto) {
        this.codigo_produto = codigo_produto;
    }

    public int getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(int codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getMarca_produto() {
        return marca_produto;
    }

    public void setMarca_produto(String marca_produto) {
        this.marca_produto = marca_produto;
    }

    public Double getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(Double preco_produto) {
        this.preco_produto = preco_produto;
    }

    public int getQuantidade_produto() {
        return quantidade_produto;
    }

    public void setQuantidade_produto(int quantidade_produto) {
        this.quantidade_produto = quantidade_produto;
    }

    public String getFornecedor_produto() {
        return fornecedor_produto;
    }

    public void setFornecedor_produto(String fornecedor_produto) {
        this.fornecedor_produto = fornecedor_produto;
    }

    public String getDescricao_produto() {
        return Descricao_produto;
    }

    public void setDescricao_produto(String descricao_produto) {
        Descricao_produto = descricao_produto;
    }
}
