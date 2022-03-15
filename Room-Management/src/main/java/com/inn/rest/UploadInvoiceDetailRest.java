package com.inn.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/rest/")
public interface UploadInvoiceDetailRest {

  @PostMapping(path = "/uploadInvoiceDetail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  String uploadInvoiceDetail(@RequestParam("username") String username, @RequestPart(name = "file") MultipartFile file)
      throws Exception;

  @GetMapping("downloadInvoiceByIdentifier/{identifier}")
  ResponseEntity<byte[]> downloadInvoiceByIdentifier(@PathVariable("identifier") String identifier) throws Exception;

}
