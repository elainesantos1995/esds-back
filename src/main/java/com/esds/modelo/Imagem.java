package com.esds.modelo;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Imagem {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String type;
	
	//Aterar a tabela após o create para possibilitar a inserção de imagens maiores
	//ALTER TABLE esds.imagem MODIFY pic_byte MEDIUMBLOB;
	@Column(name = "picByte")
	private byte[] picByte;   

	public Imagem() {
		super();
	}

	public Imagem(String name, String type, byte[] picByte) {
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}
	
	@Override
	public String toString() {
		return "Imagem [id=" + id + ", name=" + name + ", type=" + type + ", picByte=" + Arrays.toString(picByte) + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

}
