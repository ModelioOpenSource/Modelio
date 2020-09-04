/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.ui.progress;

import java.lang.reflect.InvocationTargetException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.progress.IProgressService;

@objid ("bd46e10e-0f7e-4330-a743-165afad69468")
public interface IModelioProgressService extends IProgressService {
    /**
     * This method is similar to the run() method of IProgressService.
     * However it has an additional parameter 'title' which is used as the title of the progress dialog.
     * @param title
     * @param fork
     * @param cancelable
     * @param runnable
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @objid ("04326d24-45fa-439e-82bb-d27ab2e0cba1")
    void run(String title, boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InterruptedException, InvocationTargetException;

}
