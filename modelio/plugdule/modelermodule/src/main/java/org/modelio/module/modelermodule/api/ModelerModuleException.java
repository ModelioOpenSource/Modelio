package org.modelio.module.modelermodule.api;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Default exception type used by ModelerModule.
 */
@objid ("1d3e2042-afd6-4cff-a9df-1c8703dfbb2c")
public class ModelerModuleException extends Exception {
    @objid ("77f4c91d-accc-4879-82ee-a06c51aad886")
    private static final long serialVersionUID = 982636473481714157L;

    /**
     * Default constructor.
     * @param message the error message.
     */
    @objid ("2cd32773-20c0-41aa-a51c-777bb4113692")
    public ModelerModuleException(final String message) {
        super(message);
    }

}
