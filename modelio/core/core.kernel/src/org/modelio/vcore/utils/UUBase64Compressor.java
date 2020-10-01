/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.Base64;
import org.modelio.vbasic.log.Log;

/**
 * Services to:
 * - compress and uncompress a String into a compressed Base64 UUencoded string.
 * - compress uncompress a byte[] into a compressed Base64 UUencoded string.
 * 
 * @author phv
 */
@objid ("a307bbeb-0e85-4a66-b4d1-c505dbaf0401")
public class UUBase64Compressor {
    @objid ("b3e9c2b0-6ce2-4772-9312-08f26a50ebc2")
    public static final String CHARSET = "UTF-8";

    /**
     * Compress a string
     * 
     * @param source a string
     * @return a compressed Base64 UUencoded string.
     */
    @objid ("5c3a47f5-368b-46e9-97d9-9f7b18c9ce80")
    public static String compress(String source) {
        try {
            return compressBytes(source.getBytes(UUBase64Compressor.CHARSET));
        } catch (UnsupportedEncodingException e) {
            // FIXME : this exception should be thrown !
            Log.error(e);
            return null;
        }
    }

    /**
     * Uncompress a Base64 encoded compressed string.
     * 
     * @param source the compressed string
     * @return the string uncompressed.
     */
    @objid ("d9ae23e9-0283-4f7b-8337-831e5428f71c")
    public static String decompress(String source) {
        // long start = System.currentTimeMillis();
        
        byte[] bytes = decompressAsBytes(source);
        
        // Decode the bytes into a String
        try {
            return new String(bytes, 0, bytes.length, UUBase64Compressor.CHARSET);
        } catch (UnsupportedEncodingException e) {
            // FIXME : this exception should be thrown !
            Log.error(e);
        }
        return null;
    }

    @objid ("2c881641-189c-4b00-b6a9-bfafe301f1b7")
    public static String compressBytes(byte[] sourceBytes) {
        Deflater compressor = new Deflater();
        compressor.setInput(sourceBytes);
        compressor.finish();
        
        byte[] output = new byte[sourceBytes.length];
        int compressedDataLength = compressor.deflate(output, 0, output.length, Deflater.FULL_FLUSH);
        output = Arrays.copyOf(output, compressedDataLength);
        compressor.end();
        
        String s = Base64.encode(output);
        return s;
    }

    @objid ("a7e497e5-6d6b-496f-a8aa-bfbf5d6fb682")
    public static byte[] decompressAsBytes(String source) {
        // long start = System.currentTimeMillis();
        byte[] sourceBytes = Base64.decode(source);
        if (sourceBytes == null || sourceBytes.length == 0) {
            return new byte[0];
        }
        
        // Decompress the bytes
        Inflater decompressor = new Inflater();
        decompressor.setInput(sourceBytes, 0, sourceBytes.length);
        
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(sourceBytes.length);) {
            byte[] buffer = new byte[1024];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            decompressor.end();
            return outputStream.toByteArray();
        } catch (DataFormatException | IOException e) {
            // FIXME : this exception should be thrown !
            Log.error(e);
        }
        return null;
    }

}
