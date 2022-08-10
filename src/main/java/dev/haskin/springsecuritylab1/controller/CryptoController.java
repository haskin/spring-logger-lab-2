package dev.haskin.springsecuritylab1.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springsecuritylab1.dto.CryptoDTO;
import dev.haskin.springsecuritylab1.service.CryptoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("crypto")
public class CryptoController {

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{cryptoName}")
    CryptoDTO getCryptoByName(@PathVariable String cryptoName) throws Exception {
        try {
            log.trace("entered getting crypto by name");
            CryptoDTO cryptoDTO = Optional
                    .ofNullable(restTemplate.getForObject(cryptoService.getApiUrl(cryptoName), CryptoDTO.class))
                    .orElseThrow(() -> new RestClientException(""));
            log.info("Value of retrieved cryptocurrency: {} USD", cryptoDTO.getData().getPriceUsd());
            return cryptoDTO;
        } catch (RestClientException e) {
            Optional.ofNullable(e).ifPresent(error -> {
                if (e.getMessage().contains("404"))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid crypto name was given");
            });
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "could not connect to Crypto API");
        } finally {
            log.trace("exiting getting crypto by name");
        }
    }

    @GetMapping
    String getCostOfOneBitcoin() throws ResponseStatusException {
        log.trace("entered default get one bitcoin");
        CryptoDTO cryptoDTO = Optional
                .ofNullable(restTemplate.getForObject(cryptoService.getApiUrl("bitcoin"),
                        CryptoDTO.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Could not connect to Bitcoin API"));
        log.info("Value of retrieved cryptocurrency: {} USD", cryptoDTO.getData().getPriceUsd());
        log.trace("exiting default get one bitcoin");
        return cryptoDTO.getData().getPriceUsd();
    }
}