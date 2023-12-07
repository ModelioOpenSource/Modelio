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
package org.modelio.audit.view.providers.commons;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.audit.engine.core.IAuditDiagnostic;
import org.modelio.audit.engine.core.IAuditListener;
import org.modelio.audit.plugin.Audit;

/**
 * Base implementation for content providers based on a {@link IAuditDiagnostic} input.
 * <p>
 * It handle registration of itself as an {@link IAuditListener}.
 * @author cmarin
 * @since 5.4.1
 */
@objid ("eaded572-8dc9-4ad5-be24-67bdb9ad98d9")
public abstract class AbstractDiagnosticContentProvider implements IAuditListener, ITreeContentProvider {
    @objid ("6d87612b-fdbc-4b16-b962-2ccdd1e8823d")
    private boolean outdated;

    @objid ("b89ed811-aa7a-4a60-b811-98caf923b35d")
    private IAuditDiagnostic input;

    @objid ("41924445-6fab-4356-aff8-1ccffe3ab7c9")
    @Override
    public final void auditModelChanged(IAuditDiagnostic auditDiagnostic) {
        if (auditDiagnostic == this.input) {
            this.outdated = true;
        } else {
            Audit.LOG.debug(new IllegalArgumentException("received notif for a different diagnostic"));
        }
        
    }

    @objid ("0e93df8e-f1b8-4bbc-a57a-fd3a8549e1e8")
    @Override
    public void dispose() {
        if (this.input != null ) {
            this.input.removeAuditListener(this);
            this.input = null;
        }
        
    }

    @objid ("80540ec1-a354-41ee-8cb0-4f44cfa37baa")
    @Override
    public final void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (this.input != newInput) {
            if (this.input != null) {
                this.input.removeAuditListener(this);
            }
        
            this.input = (IAuditDiagnostic) newInput;
            if (newInput != null) {
                this.input.addAuditListener(this);
            }
        }
        
        this.outdated = true;
        
    }

    @objid ("0624673b-48fd-4e5d-a76a-bfa86cae4ab7")
    protected final void refreshFromInput() {
        if (! this.outdated)
            return;
        
        this.outdated = false;
        refreshFrom(this.input);
        
    }

    @objid ("1beed9ab-93b1-48ab-b68b-5154855a5475")
    protected final IAuditDiagnostic getInput() {
        refreshFromInput();
        return this.input;
    }

    @objid ("06b9f837-a771-44a1-af80-57f6b5279d25")
    protected abstract void refreshFrom(IAuditDiagnostic input);

}
