package com.rafaelcoll.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rafaelcoll.model.Veiculo;
import com.rafaelcoll.model.VeiculoPassageiros;
import com.rafaelcoll.model.VeiculoPasseio;
import com.rafaelcoll.model.VeiculoUtilitario;

import org.apache.commons.csv.*;

public class CatalogoVeiculos {
    public List<Veiculo> veiculos = new ArrayList<>();
    
    public CatalogoVeiculos() {
        try (
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/com/rafaelcoll/resources/VeiculoPassageiros.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                veiculos.add(new VeiculoPassageiros(csvRecord.get(0), csvRecord.get(1),
                    csvRecord.get(2), Integer.parseInt(csvRecord.get(3)),
                    Double.parseDouble(csvRecord.get(4)), Integer.parseInt(csvRecord.get(5))));
            }
        } catch (IOException e) {
            System.err.println("Arquivo nao processado: " + e.getMessage());
        }

        try (
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/com/rafaelcoll/resources/VeiculoPasseio.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                veiculos.add(new VeiculoPasseio(csvRecord.get(0), csvRecord.get(1),
                    csvRecord.get(2), Integer.parseInt(csvRecord.get(3)),
                    Double.parseDouble(csvRecord.get(4)), Double.parseDouble(csvRecord.get(5))));
            }
        } catch (IOException e) {
            System.err.println("Arquivo nao processado: " + e.getMessage());
        }

        try (
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/com/rafaelcoll/resources/VeiculoUtilitario.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                veiculos.add(new VeiculoUtilitario(csvRecord.get(0), csvRecord.get(1),
                    csvRecord.get(2), Integer.parseInt(csvRecord.get(3)),
                    Double.parseDouble(csvRecord.get(4)), Double.parseDouble(csvRecord.get(5)),
                    Integer.parseInt(csvRecord.get(6))));
            }
        } catch (IOException e) {
            System.err.println("Arquivo nao processado: " + e.getMessage());
        }
    }
    
    public Veiculo consultaPorPlaca(String placa){
        return veiculos.stream()
            .filter(f -> f.getPlaca().equals(placa))
            .findAny()
            .orElse(null);
    }

    public List<Veiculo> consultaPorMarca(String marca){
        return veiculos.stream()
            .filter(f -> f.getMarca().equals(marca))
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Veiculo> consultaPorAno(int ano){
        return veiculos.stream()
            .filter(f -> f.getAno() == ano)
            .collect(Collectors.toUnmodifiableList());
    }

    public List<Veiculo> consultaPorTipo(String tipo){
        return veiculos.stream()
            .filter(f -> f.getClass().getSimpleName().equals(tipo))
            .collect(Collectors.toUnmodifiableList());
    }
}
