package net.shipilev;

import org.jivesoftware.util.Blowfish;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Decrypt
{
    public static void main( String[] args ) throws IOException {

        if (args.length < 2) {
            System.err.println("Usage: -jar decrypt.jar [in-file] [out-file]");
            return;
        }

        String in = args[0];
        String out = args[1];

        String key = "J1]{g(S4#aj*%|@+<<.^RdjR)}b{].^mb,};H>2V.R6L.Kx;b[Pv~91tv)f'~Ue!";

        // Should it be UTF8?
        Charset charset = Charset.defaultCharset();

        // That fucking ridiculous to use 3rd party lib incompatible with Openssl
        Blowfish blowfish = new Blowfish(key);
        String enc = new String(Files.readAllBytes(Paths.get(in)), charset);
        String dec = blowfish.decryptString(enc);

        PrintWriter pw = new PrintWriter(new File(out));
        pw.println(dec);
        pw.close();

    }
}
