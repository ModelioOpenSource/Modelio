/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.app.project.core.services.openproject;

import java.net.URI;
import java.net.URISyntaxException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.dialogs.auth.AuthDataDialog;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

/**
 * SWT implementation of {@link org.modelio.gproject.gproject.GProjectConfigurer.IAccessDeniedHandler}
 * and {@link IAuthenticationPrompter}.
 */
@objid ("760c0904-48a0-4279-84a4-fe25bb273281")
class AuthenticationPrompter implements IAuthenticationPrompter {
    @objid ("d7f880ef-55ce-4d4c-b79b-787add43124c")
    private Shell parentShell;

    @objid ("427b8463-32b2-4dc2-8681-6e6e5452537f")
    public AuthenticationPrompter(Shell parentShell) {
        this.parentShell = parentShell;
    }

    @objid ("cd6262e0-5484-4cfd-aefa-78e973128ddc")
    @Override
    public IAuthData promptAuthentication(IAuthData authData, String toAuthenticate, String location, String errorMessage) {
        if (location != null) {
            try {
                URI uri = new URI(location);
                return doPromptAuthentication(authData, toAuthenticate, uri.getHost(), errorMessage);
            } catch (@SuppressWarnings ("unused") URISyntaxException e) {
                // ignore and use location entirely
            }
        }
        return doPromptAuthentication(authData, toAuthenticate, location, errorMessage);
    }

    @objid ("102ca38e-c314-45f9-af8a-48587ae02d70")
    private IAuthData doPromptAuthentication(IAuthData authData, String toAuthenticate, String location, String errorMessage) {
        final IAuthData ret[] = new IAuthData[]{null} ;
        
        this.parentShell.getDisplay().syncExec( () -> {
            IAuthData initialData = authData!=null ? authData : new UserPasswordAuthData();
        
            AuthDataDialog dlg = new AuthDataDialog(  this.parentShell, initialData, toAuthenticate+" @ "+location);
            dlg.setWarningMessage(errorMessage);
            if (dlg.open() == 0) {
                ret[0] = dlg.getAuthData();
            }
        } );
        return ret[0];
    }

}
