package com.Polizas.Polizas.Persistence.Entities;

import jakarta.persistence.*;

@Entity
public class CompraPoliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String formaDePago;
    @ManyToOne
    @JoinColumn(name = "poliza_id")
    private Poliza poliza;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public CompraPoliza(){

    }

    public CompraPoliza(Long id, String formaDePago, Poliza poliza, Usuario usuario) {
        this.id = id;
        this.formaDePago = formaDePago;
        this.poliza = poliza;
        this.usuario = usuario;
    }

    public Poliza getPoliza() {
        return poliza;
    }

    public void setPoliza(Poliza poliza) {
        this.poliza = poliza;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
}
