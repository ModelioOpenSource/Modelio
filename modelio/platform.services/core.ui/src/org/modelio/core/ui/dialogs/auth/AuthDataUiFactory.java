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

package org.modelio.core.ui.dialogs.auth;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Composite;
import org.modelio.core.ui.dialogs.auth.ui.IAuthDataUi;
import org.modelio.core.ui.dialogs.auth.ui.NoDataUi;
import org.modelio.core.ui.dialogs.auth.ui.UserPasswordDataUi;
import org.modelio.vbasic.auth.IAuthData;
import org.modelio.vbasic.auth.NoneAuthData;
import org.modelio.vbasic.auth.UserPasswordAuthData;

@objid ("1f78f280-aa48-4659-bcb1-a139b97c5ed7")
class AuthDataUiFactory {
    @objid ("5f2d2662-aa95-4bb5-ad2b-9f88b921e694")
     static List<Class<? extends IAuthData>> supportedSchemes;

    @objid ("881d4130-0db0-474b-8c61-4f0af220ed02")
    public static IAuthDataUi createPanel(Composite parent, Class<? extends IAuthData> scheme) {
        if (scheme == UserPasswordAuthData.class) {
            return new UserPasswordDataUi(parent);
        } if (scheme == NoneAuthData.class) {
            return new NoDataUi(parent);
        } else {
            return null;
        }
    }

    @objid ("070007ff-7f9c-483a-81b3-3f733f5e93ee")
    public static List<Class<? extends IAuthData>> getAllSchemes() {
        if (supportedSchemes == null) {
            supportedSchemes = new ArrayList<>();
            supportedSchemes.add(UserPasswordAuthData.class);
            supportedSchemes.add(NoneAuthData.class);
        }
        return supportedSchemes;
    }

}
