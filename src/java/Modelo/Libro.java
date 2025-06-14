package Modelo;
// Generated 26-may-2025 2:25:21 by Hibernate Tools 4.3.1

import javax.persistence.*;

/**
 * Libro generated by hbm2java
 */
@Entity
@Table(name = "Libro")
@PrimaryKeyJoinColumn(name = "ID_Material")
public class Libro extends Material  implements java.io.Serializable {


    @Column(name = "ISBN")
     private String isbn;
    @Column(name = "Numero_Paginas")
     private Integer numeroPaginas;

    public Libro(){
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public Integer getNumeroPaginas() {
        return this.numeroPaginas;
    }
    
    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }




}


