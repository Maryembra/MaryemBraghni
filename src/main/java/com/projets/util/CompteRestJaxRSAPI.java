package com.projets.util;

import com.projets.entities.Compte;
import com.projets.repositories.CompteRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@Path("/banque")
public class CompteRestJaxRSAPI {
    @Autowired
    CompteRepository compteRepository;
    @Path("/comptes")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Compte>getComptes(){
        return compteRepository.findAll();
    }
    @Path("/comptes/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte getComptes(@PathParam("id") Long id){
        return compteRepository.findById(id).orElse(null);
    }
    @Path("/comptes")
    @POST
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte addCompte(Compte compte){
        return compteRepository.save(compte);
    }
    @Path("/comptes/{id}")
    @PUT
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Compte updateCompte(@PathParam("id") Long id,Compte compte){
       Compte existingCompte=compteRepository.findById(id).orElse(null);
    if(existingCompte!=null){
        existingCompte.setSolde(compte.getSolde());
        existingCompte.setDateCreation(compte.getDateCreation());
        existingCompte.setType(compte.getType());
    }
    return null;
    }
    @Path("/comptes/{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void deleteCompte(@PathParam("id") Long id){
         compteRepository.deleteById(id);
    }

}
