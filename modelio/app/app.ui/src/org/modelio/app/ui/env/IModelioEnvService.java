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
package org.modelio.app.ui.env;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.platform.core.IModelioService;
import org.modelio.platform.ui.swt.imagesselector.ImageLibrary;

@objid ("2f4d2fa2-e2ad-4f91-9e43-f96ea5c0ecb5")
public interface IModelioEnvService extends IModelioService {
    @objid ("80b4ea0f-1bcf-4389-b3d8-6cf045f3f535")
    ImageLibrary getImageLibrary();
}

