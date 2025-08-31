package br.com.pucgo.exercicio4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Classe responsável pela persistência de dados da CentralDeInformacoes
 * Utiliza a biblioteca XStream para serialização em XML
 */
public class Persistencia {

    private XStream xstream;

    /**
     * Construtor da classe Persistencia
     * Configura o XStream para trabalhar com as classes do sistema
     */
    public Persistencia() {
        this.xstream = new XStream(new DomDriver());
        // Configura aliases para tornar o XML mais legível
        xstream.alias("CentralDeInformacoes", CentralDeInformacoes.class);
        xstream.alias("Passageiro", Passageiro.class);
        xstream.alias("Sexo", Sexo.class);

        // Configurações de segurança do XStream (versões mais recentes)
        xstream.allowTypes(new Class[]{CentralDeInformacoes.class, Passageiro.class, Sexo.class});
    }

    /**
     * Salva um objeto CentralDeInformacoes em um arquivo XML
     * @param central O objeto CentralDeInformacoes a ser salvo
     * @param nomeArquivo Nome do arquivo XML onde será salvo (com ou sem extensão)
     * @return true se salvou com sucesso, false caso contrário
     */
    public boolean salvarCentral(CentralDeInformacoes central, String nomeArquivo) {
        try {
            // Adiciona extensão .xml se não foi fornecida
            if (!nomeArquivo.toLowerCase().endsWith(".xml")) {
                nomeArquivo += ".xml";
            }

            // Converte o objeto para XML
            String xml = xstream.toXML(central);

            // Salva o XML no arquivo
            try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
                fos.write(xml.getBytes("UTF-8"));
                fos.flush();
            }

            return true;

        } catch (IOException e) {
            System.err.println("Erro ao salvar a central: " + e.getMessage());
            return false;
        }
    }

    /**
     * Recupera um objeto CentralDeInformacoes de um arquivo XML
     * @param nomeArquivo Nome do arquivo XML de onde será recuperado
     * @return CentralDeInformacoes recuperada do arquivo, ou nova instância se arquivo não existir
     */
    public CentralDeInformacoes recuperarCentral(String nomeArquivo) {
        try {
            // Adiciona extensão .xml se não foi fornecida
            if (!nomeArquivo.toLowerCase().endsWith(".xml")) {
                nomeArquivo += ".xml";
            }

            File arquivo = new File(nomeArquivo);

            // Se o arquivo não existir, retorna uma nova CentralDeInformacoes
            if (!arquivo.exists()) {
                return new CentralDeInformacoes();
            }

            // Lê o conteúdo do arquivo
            StringBuilder xml = new StringBuilder();
            try (FileInputStream fis = new FileInputStream(arquivo)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    xml.append(new String(buffer, 0, bytesRead, "UTF-8"));
                }
            }

            // Converte o XML de volta para objeto
            CentralDeInformacoes central = (CentralDeInformacoes) xstream.fromXML(xml.toString());
            return central;

        } catch (IOException e) {
            System.err.println("Erro ao recuperar a central: " + e.getMessage());
            return new CentralDeInformacoes();
        } catch (Exception e) {
            System.err.println("Erro ao processar o arquivo XML: " + e.getMessage());
            return new CentralDeInformacoes();
        }
    }
}
