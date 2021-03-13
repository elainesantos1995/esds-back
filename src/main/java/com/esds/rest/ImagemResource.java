package com.esds.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.esds.modelo.Beneficiario;
import com.esds.modelo.Imagem;
import com.esds.repositorio.Imagens;
import com.esds.servico.impl.BeneficiarioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/image")
public class ImagemResource {

	@Autowired
	Imagens imageRepository;
	
	@Autowired
	BeneficiarioServiceImpl beneficiarios;
	
	@PostMapping("/upload/{idBeneficiario}")
	@ResponseStatus(HttpStatus.CREATED)
	public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file, @PathVariable Integer idBeneficiario) throws IOException {

	//	System.out.println("Original Image Byte Size - " + file.getBytes().length);

		Imagem img = new Imagem(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));

		Imagem imgSalva = imageRepository.save(img); 
		
		Beneficiario beneficiario = beneficiarios.buscarPorId(idBeneficiario);
		beneficiario.setImagem(imgSalva);
		beneficiarios.atualizar(idBeneficiario, beneficiario);		
		
		return (BodyBuilder) ResponseEntity.status(HttpStatus.CREATED);
	}
	
	@PostMapping("/upload")
	@ResponseStatus(HttpStatus.CREATED)
	public BodyBuilder uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {	
		Imagem img = new Imagem(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));

		imageRepository.save(img); 
		
		return (BodyBuilder) ResponseEntity.status(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/image/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteImage(@RequestParam("id") Integer id) throws IOException {
		
		Optional<Imagem> imagemRetornada = imageRepository.findById(id);
		Imagem imagemRemovida = null;
		if(imagemRetornada.isPresent()) {
			imagemRemovida = new Imagem();
			imagemRemovida.setId(id);
			imageRepository.delete(imagemRemovida);
		}		
	}
	
	@PutMapping("/upload/image/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateImage(@PathVariable("id") Integer id, @RequestParam("imageFile") MultipartFile file) throws IOException {
		
		Imagem img = new Imagem(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		img.setId(id);
		imageRepository.save(img);		
		
	}

	@GetMapping(path = { "/get/{imageName}" })
	public Imagem getImage(@PathVariable("imageName") String imageName) throws IOException {

		final Optional<Imagem> retrievedImage = imageRepository.findByName(imageName);

		Imagem img = new Imagem(retrievedImage.get().getName(), retrievedImage.get().getType(),

				decompressBytes(retrievedImage.get().getPicByte()));

		return img;

	}
	
	@GetMapping(path = { "/get/id/{id}" })
	public Imagem getImageForId(@PathVariable("id") Integer id) throws IOException {

		final Optional<Imagem> retrievedImage = imageRepository.findById(id);

		Imagem img = new Imagem(retrievedImage.get().getName(), retrievedImage.get().getType(),

				decompressBytes(retrievedImage.get().getPicByte()));

		return img;
	}
	
	// Comprime osbytes da imagem antes de salvar no banco de dados
	public static byte[] compressBytes(byte[] data) {

		Deflater deflater = new Deflater();

		deflater.setInput(data);

		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

		byte[] buffer = new byte[1024];

		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}

		try {
			outputStream.close();
		} catch (IOException e) {
		}

		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();

	}

	public static byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}
		return outputStream.toByteArray();
	}

}
