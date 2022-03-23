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
package org.modelio.vcore.session.impl.storage;

import java.nio.file.FileSystemException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.files.FileUtils;
import org.modelio.vcore.session.api.repository.IRepository;

/**
 * Indicates that a storage error occurs.
 * <p>
 * The exception contains an error message and the repository where
 * the error occurred.
 */
@objid ("00682428-fd1a-1f27-a7da-001ec947cd2a")
public class StorageException extends Exception {
    @objid ("00683724-fd1a-1f27-a7da-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    @objid ("0d2bdd5f-d66d-11e1-adbb-001ec947ccaf")
    private IRepository repository;

    /**
     * Initialize from an exception.
     * <p>
     * If the exception is a {@link FileSystemException}, a user friendly message is computed.
     * @param repository the repository where the error occurred.
     * @param cause the error that occurred
     */
    @objid ("00684d22-fd1a-1f27-a7da-001ec947cd2a")
    public  StorageException(IRepository repository, final Throwable cause) {
        super(computeMsg(cause), cause);
        this.repository = repository;
        
    }

    /**
     * initialize from an error message.
     * @param repository the repository where the error occurred.
     * @param message the error message
     */
    @objid ("0068635c-fd1a-1f27-a7da-001ec947cd2a")
    public  StorageException(IRepository repository, final String message) {
        super(message);
        this.repository = repository;
        
    }

    /**
     * @param repository the repository where the error occurred.
     * @param message the error message
     * @param cause the cause of this exception.
     */
    @objid ("00689854-fd1a-1f27-a7da-001ec947cd2a")
    public  StorageException(IRepository repository, final String message, final Throwable cause) {
        super(message, cause);
        this.repository = repository;
        
    }

    /**
     * @return the repository where the error occurred.
     */
    @objid ("0d2bdd69-d66d-11e1-adbb-001ec947ccaf")
    public IRepository getRepository() {
        return this.repository;
    }

    @objid ("0d2bdd6e-d66d-11e1-adbb-001ec947ccaf")
    private static String computeMsg(Throwable cause) {
        if (cause instanceof FileSystemException)
            return FileUtils.getLocalizedMessage((FileSystemException) cause);
        else
            return cause.getLocalizedMessage();
        
    }

}
