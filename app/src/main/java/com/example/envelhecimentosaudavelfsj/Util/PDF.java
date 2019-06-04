package com.example.envelhecimentosaudavelfsj.Util;

import android.support.annotation.NonNull;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * Created by Yan Vitor on 03/06/2019.
 */

public class PDF
{
    public static  Boolean salvar(@NonNull String nomeArquivo,@NonNull String caminho)
    {
        try {

            String fpath = caminho + "/" + nomeArquivo + ".pdf"; //declara o caminho final do arquivo
            File file = new File(fpath);

            if (!file.exists()) {
                file.createNewFile(); //caso arquivo não existe , cria um novo , se não o arquivo será substituído
            }

            Document documento = new Document();

            PdfWriter.getInstance(documento,new FileOutputStream(file.getAbsoluteFile())); //Classe PdfWriter é responsavel por salvar o arquivo PDF com o conteúdo

            documento.open();

            //region

            documento.add(new Paragraph("Hello World")); //Exemplo de parágrafo

            //endregion

            documento.close();

            return true;

        } catch (IOException e)
        {
            e.printStackTrace();
            return false;

        } catch (DocumentException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
