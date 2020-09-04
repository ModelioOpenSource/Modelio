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

package org.modelio.vcore.model.spi;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.model.api.IModelFactory;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("20fffb94-87ef-4099-95fa-ce0a7830f94e")
public abstract class AbstractModelFactory implements IModelFactory {
    @objid ("cdaf9691-8e00-4ed3-a9f3-6d6cd21e7670")
    protected final MMetamodel metamodel;

    @objid ("90bd2681-5b8c-4911-9d58-2b1bf4618827")
    public AbstractModelFactory(MMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    @objid ("cf540807-f85b-4e52-9e3a-784fa39c2209")
    @Override
    public MObject createElement(String metaclassName) {
        MClass mc = this.metamodel.getMClass(metaclassName);
        return createElement(mc);
    }

    @objid ("4dc92476-6493-4ac4-86bd-5d0356587cfd")
    @Override
    public MObject createElement(String metaclassName, MObject owner, String dependencyName) {
        MClass mc = this.metamodel.getMClass(metaclassName);
        MDependency dep = mc.getDependency(dependencyName);
        return createElement(mc, owner, dep);
    }

    @objid ("cd7a6a69-66be-401b-8c8e-ef0806b21fe3")
    @Override
    @SuppressWarnings("unchecked")
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass) {
        MClass mc = this.metamodel.getMClass(metaclass);
        return (T) createElement(mc);
    }

    @objid ("04598130-15b5-4ed1-9a32-10be34554650")
    @Override
    @SuppressWarnings("unchecked")
    public <T extends MObject> T createElement(java.lang.Class<T> metaclass, MObject owner, String dependencyName) {
        MClass mc = this.metamodel.getMClass(metaclass);
        MDependency dep = mc.getDependency(dependencyName);
        return (T) createElement(mc, owner, dep);
    }

    /**
     * @return a new model object builder.
     */
    @objid ("f6f43a35-9457-46f1-83c4-439d7b4f8708")
    public Builder<MObject> builder() {
        return new Builder<>();
    }

    /**
     * Builder design pattern.
     * @author cmarin
     * @since 3.6
     * 
     * @param <T> the final type of the created element.
     */
    @objid ("aefb0cff-8bd1-478d-a06d-e75d14439746")
    public class Builder<T extends MObject> {
        @objid ("82e318af-0961-4b17-a5c4-b5e1fa0ca06b")
        private MClass mc;

        @objid ("d8ec90d4-50b4-4ba7-aa30-a4aa49146e2a")
        private MObject owner;

        @objid ("e78340c2-5c44-4ae9-bd29-0e1f14a29fc3")
        private MDependency dep;

        @objid ("8d52dfc1-accb-4ac1-b881-b7d173597132")
        @SuppressWarnings("unchecked")
        public <U extends MObject> Builder<U> withClass(java.lang.Class<U> metaclass) {
            this.mc = AbstractModelFactory.this.metamodel.getMClass(metaclass);
            return (Builder<U>) this;
        }

        @objid ("a6cfed84-9a20-4223-ac28-7d81dcc87d6c")
        public Builder<T> withClass(String metaclassName) {
            this.mc = AbstractModelFactory.this.metamodel.getMClass(metaclassName);
            return this;
        }

        @objid ("ad9fc937-11b5-4bc1-8ffb-798df653f496")
        public Builder<T> withClass(MClass metaclass) {
            this.mc = (metaclass);
            return this;
        }

        @objid ("7b5e799c-6b88-41f0-b16e-d4b8eda9829a")
        public Builder<T> withOwner(MObject anOwner) {
            this.owner = anOwner;
            return this;
        }

        @objid ("7bf5f11f-5af3-4da5-8014-7c12f3d3c658")
        public Builder<T> withDep(String dependencyName) {
            if (this.mc == null)
                throw new IllegalStateException("No metaclass defined");
            
            this.dep = this.mc.getDependency(dependencyName);
            if (this.dep == null)
                throw new IllegalArgumentException(String.format("%s metaclass does not have a '%s' dependency", this.mc.getQualifiedName(), dependencyName));
            return this;
        }

        @objid ("c05bb514-c82a-4c66-9ce9-ed5e7162042f")
        @SuppressWarnings("unchecked")
        public T create() {
            if (this.mc == null)
                throw new IllegalStateException("No metaclass defined.");
            
            if (this.owner == null)
                return (T) createElement(this.mc);
            else
                return (T) createElement(this.mc, this.owner, this.dep);
        }

    }

}
