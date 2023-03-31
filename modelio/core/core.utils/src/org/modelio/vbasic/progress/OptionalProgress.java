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
package org.modelio.vbasic.progress;

import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * IModelioProgress implementation that consumes and allocates nothing on its parent
 * if not used.
 * @author cmarin
 * @since 5.3.1
 */
@objid ("af070947-e370-40ba-9091-5eb22b0d0f81")
public class OptionalProgress implements IModelioProgress {
    @objid ("819f6c81-c8c0-4394-85fa-73727008c710")
    protected final Supplier<? extends IModelioProgress> supplier;

    @objid ("3f139242-c236-432d-a393-7c1ddcae16e9")
    private IModelioProgress wrapped;

    @objid ("ab555233-f3d4-41e0-bdd9-f6c31b630b36")
    protected final IModelioProgress parent;

    @objid ("ec7fe90d-5fbe-4887-bbcf-34c7561c79b5")
    public  OptionalProgress(IModelioProgress parent, Supplier<? extends IModelioProgress> supplier) {
        this.parent = parent;
        this.supplier = supplier;
        
    }

    @objid ("7949d97e-5522-4f57-8382-dcc643702109")
    protected final IModelioProgress getWrapped() {
        if (this.wrapped == null)
            this.wrapped = createWrapped();
        return this.wrapped;
    }

    @objid ("abb0528b-6cb0-4ec6-a657-ae250deddff6")
    protected IModelioProgress createWrapped() {
        return this.supplier.get();
    }

    @objid ("5acbf039-05fa-4ad8-a013-0d19c3dce6f5")
    @Override
    public void beginTask(String name, int totalWork) {
        getWrapped().beginTask(name, totalWork);
    }

    @objid ("3f4ebe82-4ebf-40f3-8634-87dba862a934")
    @Override
    public void done() {
        if (this.wrapped != null)
            this.wrapped.done();
        
    }

    @objid ("b29607bd-018f-42a5-9efc-f7d3cb6704dd")
    @Override
    public void internalWorked(double work) {
        getWrapped().internalWorked(work);
    }

    @objid ("e406b06d-4e2c-4bb1-82eb-03a47e23f0d4")
    @Override
    public boolean isCanceled() {
        if (this.wrapped == null)
            return this.parent.isCanceled();
        return getWrapped().isCanceled();
    }

    @objid ("5a4c45f7-71db-48b1-aab7-7b6030237766")
    @Override
    public void setCanceled(boolean value) {
        if (this.wrapped == null)
            this.parent.setCanceled(value);
        else
            getWrapped().setCanceled(value);
        
    }

    @objid ("c6a3861e-28b9-4d82-879a-b109e18cf6ba")
    @Override
    public void setTaskName(String name) {
        getWrapped().setTaskName(name);
    }

    @objid ("7de6c90f-97f3-43cd-a656-44456dda0bec")
    @Override
    public void subTask(String name) {
        getWrapped().subTask(name);
    }

    @objid ("c89fa695-9411-454a-9b8e-cf3749824024")
    @Override
    public void worked(int work) {
        getWrapped().worked(work);
    }

}
