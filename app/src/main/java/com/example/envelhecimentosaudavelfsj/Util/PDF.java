package com.example.envelhecimentosaudavelfsj.Util;

import android.support.annotation.NonNull;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Created by Yan Vitor on 03/06/2019.
 */

@SuppressWarnings("ALL")
public class PDF {
    public static Boolean salvar(@NonNull String nomeArquivo, @NonNull String caminho, String nome, String idade, String sexo, String peso,
                                 String IMC, String frequenciaCardiaca, String altura, String RCQ, String PressaoArterial, String dobrasCutaneasResultado,
                                 String Distancia, String PressaoArterialPre, String PressaoArterialPos, String VolumeMaximoOxigenioSangue
    ) {
        try {

            String caminhoFinal = caminho + "/" + nomeArquivo + ".pdf"; //declara o caminho final do arquivo
            File file = new File(caminhoFinal);

            if (!file.exists()) {
                file.createNewFile(); //caso arquivo não existe , cria um novo , se não o arquivo será substituído
            }

            Document documento = new Document();

            PdfWriter.getInstance(documento, new FileOutputStream(file.getAbsoluteFile())); //Classe PdfWriter é responsavel por salvar o arquivo PDF com o conteúdo

            documento.open();

            Font boldFont = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 15, Font.NORMAL);

            //region Corpo do documento


            documento.add(new Phrase(nome, boldFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("Idade: " + idade + "     sexo: " + sexo, normalFont));
            documento.add(new Paragraph((" ")));

            documento.add(new Phrase("Antropometria:", boldFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("Peso: " + peso + "     IMC: " + IMC + "     F.C: " + frequenciaCardiaca, normalFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("Altura: " + altura + "     RCQ: " + RCQ + "    P.A: " + PressaoArterial, normalFont));
            documento.add(new Paragraph((" ")));

            documento.add(new Phrase("Dobras Cutâneas:", boldFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("Resultado em Percentual: " + dobrasCutaneasResultado + "%", normalFont));
            documento.add(new Paragraph((" ")));

            documento.add(new Phrase("Teste de Caminhada:", boldFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("Distância(Km): " + Distancia + "   Pressão Arterial Pré-Teste: " + PressaoArterialPre, normalFont));
            documento.add(new Paragraph((" ")));
            documento.add(new Phrase("VO²Max: " + VolumeMaximoOxigenioSangue + "           Pressão Arterial Pós-Teste: " + PressaoArterialPos));

            //endregion

            documento.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;

        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
