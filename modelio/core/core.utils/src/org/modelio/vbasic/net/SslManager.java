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

package org.modelio.vbasic.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;

/**
 * Creates and configure a customized {@link SSLContext} to manage https URIs.
 * <p>
 * Allows to add temporary or permanently trusted certificates.
 */
@objid ("98177ca1-4334-423a-9833-66ed25589f78")
public class SslManager {
    /**
     * Password to check integrity of certificates database.
     */
    @objid ("414875ad-140b-4e08-b256-c2f0cc3f33a7")
    private static final char[] defaultPassword = "modelio".toCharArray();

    @objid ("387add16-f5a5-4482-a088-f721910eb512")
    private static final SslManager instance = new SslManager();

    @objid ("694850a3-65e5-4cec-acef-493b3c7ad73a")
    private SSLContext sslContext;

    @objid ("8e9eee92-c50a-4ade-8aa0-ec012591b623")
    private X509TrustManagerImplementation trustManager;

    @objid ("d6e80cc9-0c88-4ed9-8853-854b7ffcda8a")
    private ISslUntrustedServerFixer untrustedServerFixer;

    /**
     * @return the singleton instance.
     */
    @objid ("9272c2b1-4c7b-4671-ad3c-0d2634216676")
    public static SslManager getInstance() {
        return instance;
    }

    /**
     * Initialize server certificate truster.
     */
    @objid ("392a0a1d-cd7e-4320-bcef-f3318ee4ba31")
    private SslManager() {
        try {
            this.sslContext = SSLContext.getInstance("TLS");
            this.trustManager = new X509TrustManagerImplementation();
        
            this.sslContext.init(null, new TrustManager[] { this.trustManager }, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(this.sslContext.getSocketFactory());
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            // Should never happen
            throw new Error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Add a trusted certificate.
     * 
     * @param cert the certificate to trust
     * @param permanent <code>true</code> to trust it permanently, <code>false</code> to trust it until the JVM exits.
     * @throws java.security.KeyStoreException if the certificate cannot be permanently stored.
     */
    @objid ("d92ceb2a-52ff-4a3b-b730-a524c5b79fd7")
    public void addCertificate(X509Certificate cert, boolean permanent) throws KeyStoreException {
        this.trustManager.addCertificate(cert, permanent);
    }

    /**
     * Set the file path where permanent trusted certificates are stored.
     * 
     * @param trustStoreFile the trust store path.
     * @param trustStorePassword the trust store password
     * @throws java.io.IOException in case of failure reading the file.
     * @since 4.0
     */
    @objid ("5a93acc9-25c2-4e24-bb9d-3bd92d9ebcd1")
    public void setTrustStoreFile(Path trustStoreFile, char[] trustStorePassword) throws IOException {
        this.trustManager.init(trustStoreFile, trustStorePassword);
    }

    /**
     * @return the SSL context to use to create SSL connections
     */
    @objid ("3593b35c-3700-48b8-914d-c543b3b2b126")
    public SSLContext getSslContext() {
        return this.sslContext;
    }

    /**
     * Allow the {@link #getUntrustedServerFixer()} to fix the trust error.
     * <p>
     * Returns <i>false</i> if no trust fixer has been registered.
     * <p>
     * If the method returns true the caller may try the connection again.
     * 
     * @param uri the URI whose connection failed
     * @param certChain the certificate chain whose validation failed
     * @param error the SSL connection error
     * @return <i>true</i> if connection can be tried again, <i>false</i> if it should be aborted.
     */
    @objid ("3df8e954-4eb1-4f9c-9531-2c95ace3c624")
    public boolean fixUntrustedServer(URI uri, X509Certificate[] certChain, Throwable error) {
        if (this.untrustedServerFixer != null)
            return this.untrustedServerFixer.fixUntrustedServer(uri, certChain, error);
        else
            return false;
    }

    /**
     * Allow the {@link #getUntrustedServerFixer()} to fix the trust error.
     * <p>
     * Returns <i>false</i> if no trust fixer has been registered.
     * <p>
     * If the method returns <i>true</i> the caller may try the connection again.
     * 
     * @param ex the SSL exception
     * @param uri the URI whose connection failed
     * @return <i>true</i> if connection can be tried again, <i>false</i> if it should be aborted.
     */
    @objid ("c3adb50c-c968-433e-a768-aaf490f11109")
    public boolean fixUntrustedServer(SSLException ex, URI uri) {
        if (this.untrustedServerFixer != null) {
            final InvalidCertificateException certExc = getInvalidCerts(ex);
            if (certExc != null) {
                return this.untrustedServerFixer.fixUntrustedServer(uri, certExc.getCertChain(), certExc);
            }
        }
        return false;
    }

    /**
     * @return the SSL non trusted server fixer.
     */
    @objid ("3b263aff-ec7d-42d6-8afc-72a8cd849c9f")
    public ISslUntrustedServerFixer getUntrustedServerFixer() {
        return this.untrustedServerFixer;
    }

    /**
     * Set the handler that can fix non trusted SSL server.
     * 
     * @param sslTrustProblemFixer the trust fixer.
     */
    @objid ("cc10d7fc-4800-4903-a157-f4d78a8a397a")
    public void setUntrustedServerFixer(ISslUntrustedServerFixer sslTrustProblemFixer) {
        this.untrustedServerFixer = sslTrustProblemFixer;
    }

    /**
     * Get the X509TrustManager configured by this manager.
     * <p>
     * This trust manager throws a {@link InvalidCertificateException} when a server certificate is not valid.
     * 
     * @return the X509TrustManager.
     */
    @objid ("1241e008-340a-40a0-9c16-ffb82131f81a")
    public X509TrustManager getTrustManager() {
        return this.trustManager;
    }

    /**
     * Look for an {@link InvalidCertificateException} exception if the exception,
     * its cause and suppressed exceptions graph and return it if found.
     * 
     * @param e an exception
     * @return the found InvalidCertificateException or <i>null</i>
     */
    @objid ("c9ceb5e9-f4bb-4644-a46b-2d0f83815387")
    private static InvalidCertificateException getInvalidCerts(Throwable e) {
        if (e instanceof InvalidCertificateException) {
            return (InvalidCertificateException) e;
        }
        
        if (e.getCause() != null) {
            InvalidCertificateException ret = getInvalidCerts(e.getCause());
            if (ret != null)
                return ret;
        }
        
        for (Throwable supEx : e.getSuppressed()) {
            InvalidCertificateException ret = getInvalidCerts(supEx);
            if (ret != null)
                return ret;
        }
        return null;
    }

    /**
     * Set the file path where permanent trusted certificates are stored.
     * 
     * @param trustStoreFile the trust store path.
     * @throws java.io.IOException in case of failure reading the file.
     * @deprecated since 4.0 Use {@link #setTrustStoreFile(Path, char[])} instead
     */
    @objid ("fb4c3459-cba6-476c-870f-02191677e1e0")
    public void setTrustStoreFile(Path trustStoreFile) throws IOException {
        this.trustManager.init(trustStoreFile, defaultPassword);
    }

    /**
     * X509 trust manager that delegates to default trust managers but
     * allows temporary or permanently trusted certificates.
     * 
     * @see X509TrustManager
     */
    @objid ("14e51ce2-0c70-472f-bebe-fc1c63dfb3ee")
    private static final class X509TrustManagerImplementation extends X509ExtendedTrustManager {
        /**
         * Default X509TrustManager implementations to delegate to.
         */
        @objid ("30836fc6-4243-4ebc-8ee0-d0088c044f34")
        private final List<X509TrustManager> defTrustManagers;

        /**
         * concatenation of all default accepted issuers.
         */
        @objid ("f48c4930-202f-4d2f-a699-b9f2f368ddcf")
        private final X509Certificate[] acceptedIssuers;

        /**
         * Persistent Trust store containing permanently accepted certificates.
         */
        @objid ("99d42bc8-0848-4a32-b5b9-4e5594547327")
        private KeyStore persistentTrustStore;

        /**
         * Temporarily accepted certificates.
         */
        @objid ("12629063-8625-4718-8066-4ee06a0f6005")
        private Collection<X509Certificate> tempTrustStore;

        @objid ("7cf3798e-395e-47b8-aacc-ae8db210319f")
        private Path trustStoreFile;

        @objid ("ef0ea38c-b4c6-48ec-82c9-1cd782cec12d")
        X509TrustManagerImplementation() throws KeyStoreException, NoSuchAlgorithmException {
            // Get a TrustManagerFactory
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            this.defTrustManagers  = new ArrayList<>();
            final ArrayList<X509Certificate> accepted = new ArrayList<>();
            
            trustManagerFactory.init((KeyStore)null);
            
            // Get all registered trust managers and add them to our delegate list
            // Also compute all accepted issuers
            for (TrustManager tm : trustManagerFactory.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    X509TrustManager xtm = (X509TrustManager) tm;
                    this.defTrustManagers.add(xtm);
            
                    for (X509Certificate iss : xtm.getAcceptedIssuers())
                        accepted.add(iss);
                }
            }
            this.acceptedIssuers = accepted.toArray(new X509Certificate[accepted.size()]);
            
            // Initialize the temporary accepted certificates.
            this.tempTrustStore= new HashSet<>();
        }

        /**
         * Initialize and load the permanently accepted certificates store.
         * 
         * @param aStoreFile the certificate store file path.
         * @throws java.io.IOException in case of failure
         */
        @objid ("56424e26-3f62-47b6-8a17-b38ab47d5829")
        void init(Path aStoreFile, char[] filePassword) throws IOException {
            try {
                this.persistentTrustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                this.trustStoreFile = aStoreFile;
            
                if (Files.isRegularFile(aStoreFile)) {
                    try (InputStream is = new BufferedInputStream(Files.newInputStream(aStoreFile));) {
            
                        this.persistentTrustStore.load(is, filePassword);
                    }
                } else {
                    this.persistentTrustStore.load(null, filePassword);
                }
            } catch (NoSuchAlgorithmException | CertificateException | KeyStoreException e) {
                throw new IOException(e.getLocalizedMessage(), e);
            }
        }

        /**
         * Add a trusted certificate.
         * 
         * @param cert the certificate to trust
         * @param permanent <code>true</code> to trust it permanently, <code>false</code> to trust it until the JVM exits.
         * @throws java.security.KeyStoreException if the certificate cannot be permanently stored.
         */
        @objid ("b8c7566f-f680-48fa-b08e-f4310119ee26")
        public void addCertificate(X509Certificate cert, boolean permanent) throws KeyStoreException {
            if (permanent) {
                if (this.persistentTrustStore == null)
                    throw new IllegalStateException("Trusted certificate store not loaded.");
            
                String alias = hashName(cert);
                this.persistentTrustStore.setCertificateEntry(alias, cert);
            
                save();
            } else {
                this.tempTrustStore.add(cert);
            }
        }

        @objid ("4cd289de-6cd4-4848-a123-2e70d732053e")
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return this.acceptedIssuers;
        }

        /**
         * Save the permanently accepted certificate store.
         * 
         * @throws java.security.KeyStoreException in case of failure.
         */
        @objid ("1ad7a2f9-d72f-4a3f-a7d0-b7e5edbd1bac")
        @SuppressWarnings("synthetic-access")
        public void save() throws KeyStoreException {
            if (this.trustStoreFile!= null) {
                try (OutputStream os = new BufferedOutputStream(Files.newOutputStream(this.trustStoreFile))) {
                    this.persistentTrustStore.store(os, defaultPassword);
                } catch (FileSystemException e) {
                    throw new KeyStoreException(FileUtils.getLocalizedMessage(e),e);
                } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
                    throw new KeyStoreException(e.getLocalizedMessage(),e);
                }
            }
        }

        @objid ("c7a9e6a0-971d-40f1-99f3-cae6a72a5c6f")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            for (X509TrustManager i : this.defTrustManagers )
                i.checkClientTrusted(chain, authType);
        }

        @objid ("46884ec8-ae76-4e5e-9ced-5d672428aeb1")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
            // Call default behavior
            for (X509TrustManager i : this.defTrustManagers )
                if (i instanceof X509ExtendedTrustManager)
                    ((X509ExtendedTrustManager)i).checkClientTrusted(chain, authType, socket);
        }

        @objid ("65a30a5d-26b8-44fe-99f5-0cf8eaa655fa")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
            // Call default behavior
            for (X509TrustManager i : this.defTrustManagers )
                if (i instanceof X509ExtendedTrustManager)
                    ((X509ExtendedTrustManager)i).checkClientTrusted(chain, authType, engine);
        }

        @objid ("ac10c317-ce0f-4ef0-a2b1-8fbfd6b6d4e8")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            if (isTrustedByUser(chain))
                return;
            
            // Call default behavior
            try {
                for (X509TrustManager i : this.defTrustManagers )
                    i.checkServerTrusted(chain, authType);
            } catch (CertificateException e) {
                throw new InvalidCertificateException(chain, e);
            }
        }

        @objid ("0b6f1dee-5271-43fa-9433-f6827b09c75b")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
            if (isTrustedByUser(chain))
                return;
            
            // Call default behavior
            try {
                for (X509TrustManager i : this.defTrustManagers )
                    if (i instanceof X509ExtendedTrustManager)
                        ((X509ExtendedTrustManager)i).checkServerTrusted(chain, authType, socket);
            } catch (CertificateException e) {
                throw new InvalidCertificateException(chain, e);
            }
        }

        @objid ("38386588-a29a-49a2-a98c-ed2c9796b991")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
            if (isTrustedByUser(chain))
                return;
            
            // Call default behavior
            try {
                for (X509TrustManager i : this.defTrustManagers )
                    if (i instanceof X509ExtendedTrustManager)
                        ((X509ExtendedTrustManager)i).checkServerTrusted(chain, authType, engine);
            } catch (CertificateException e) {
                throw new InvalidCertificateException(chain, e);
            }
        }

        @objid ("611829a0-7f64-43c7-94d3-13dcb5c2fc6c")
        private boolean isTrustedByUser(X509Certificate[] chain) throws InvalidCertificateException {
            X509Certificate targetCert = chain[0];
            
            // Look in persistent store
            if (this.persistentTrustStore != null) {
                try {
                    if (this.persistentTrustStore.getCertificateAlias(targetCert) != null)
                        return true;
                } catch (KeyStoreException e) {
                    throw new InvalidCertificateException(chain, e);
                }
            }
            
            // Look in transient store
            return (this.tempTrustStore.contains(targetCert));
        }

        @objid ("7f2063c7-7993-4531-b587-ac012dee552d")
        private static String hashName(X509Certificate cert) {
            return cert.getIssuerX500Principal().getName()+cert.getSerialNumber().toString(36);
        }

    }

}
