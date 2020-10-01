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

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Extends CertificateException to include the invalid certificate chain.
 */
@objid ("69467c4b-a363-40d2-93da-1ac0f2dda1e8")
public class InvalidCertificateException extends CertificateException {
    @objid ("3584f188-fa42-4f11-88be-a3eeaebd9a37")
    private String msg;

    @objid ("02c6dd62-c0da-446c-9cf9-dac42fd0721b")
    private static final long serialVersionUID = 1L;

    @objid ("bc267c8d-becd-4ace-a9ae-23b5a7fbab33")
    private final X509Certificate[] chain;

    /**
     * initialize the exception.
     * 
     * @param chain the invalid certificate chain
     * @param e the exception cause
     */
    @objid ("0a7a49a1-46f7-4fcb-80eb-652ebd0f1fb5")
    public InvalidCertificateException(X509Certificate[] chain, Throwable e) {
        super(e);
        this.chain = chain;
        
        this.msg = e.getLocalizedMessage();
        
        if (e.getClass().getName().equals("sun.security.validator.ValidatorException")) {
            // Try to make the error message more user friendly.
            if (e.getCause() != null)
                this.msg = e.getCause().getLocalizedMessage(); 
        }
    }

    @objid ("a0eb6c2f-21ba-4fb3-bec1-34726752c77f")
    @Override
    public String getMessage() {
        return this.msg;
    }

    /**
     * Get the certificate chain whose validation failed.
     * 
     * @return in invalid certificate chain.
     */
    @objid ("8f29fa9a-2e64-4f27-8eab-d713ada8c0a8")
    public X509Certificate[] getCertChain() {
        return this.chain;
    }

}
