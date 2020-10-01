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

package org.modelio.vcore.smkernel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vbasic.version.Version;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.ISmMetamodelFragment;
import org.modelio.vcore.smkernel.meta.SmAttribute;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmMetamodel;
import org.modelio.vcore.smkernel.meta.smannotations.SmDirective;

/**
 * SmObject Metaclass
 */
@objid ("00328f2a-702c-1f21-85a5-001ec947cd2a")
public class SmObjectSmClass extends SmClass {
    @objid ("d5c1bddb-cfe8-47e0-ba39-4a0dc20a6670")
    public static final String MNAME = "SmObject";

    @objid ("8d4e5256-fa3a-446c-a8cc-721f9648875e")
    public static final String MQNAME = "modelio.kernel.SmObject";

    @objid ("0032bd7e-702c-1f21-85a5-001ec947cd2a")
    private SmAttribute pstatusAtt;

    /**
     * Default constructor.
     * 
     * @param origin the owner metamodel fragment
     */
    @objid ("873f779f-a0f3-43b1-9e1a-f74b201574bf")
    public SmObjectSmClass(ISmMetamodelFragment origin) {
        super(origin);
    }

    @objid ("1907387d-4fa5-4388-abd9-cdac4f0935bd")
    @Override
    public String getName() {
        return MNAME;
    }

    @objid ("2042d3a8-c2be-4a42-a453-438ed334861a")
    @Override
    public boolean isAbstract() {
        return true;
    }

    @objid ("a6e1024d-fd8d-4b3a-9559-de825ae3d808")
    @Override
    public boolean isCmsNode() {
        return false;
    }

    @objid ("f9c5b23c-26e0-43b6-b333-8b56e007d784")
    @Override
    public void load(SmMetamodel m) {
        this.pstatusAtt = new StatusSmAttribute();
        this.pstatusAtt.init("status", this, Long.class, SmDirective.SMCDPARTOF);
        
        registerAttribute(this.pstatusAtt);
        registerFactory(new ObjectFactory());
    }

    /**
     * @return the "status" meta attribute.
     */
    @objid ("00330090-702c-1f21-85a5-001ec947cd2a")
    public SmAttribute statusAtt() {
        if (this.pstatusAtt == null) {
            this.pstatusAtt = getAttributeDef("status");
        }
        return this.pstatusAtt;
    }

    @objid ("54d98126-5ebe-445c-a5a9-90f9a6df6b40")
    @Override
    public Version getVersion() {
        return new Version(0, 0, 0);
    }

    @objid ("fc1150d6-516a-423a-a0a7-4f1e46dacf96")
    @Override
    public Class<? extends MObject> getJavaInterface() {
        return MObject.class;
    }

    /**
     * The factory for SmObjectData
     */
    @objid ("00256408-fd1a-1f27-a7da-001ec947cd2a")
    public static class ObjectFactory implements ISmObjectFactory {
        @objid ("003aa08e-fd1a-1f27-a7da-001ec947cd2a")
        @Override
        public ISmObjectData createData() {
            throw new RuntimeException("SmObject is abstract.");
        }

        @objid ("003ac1ea-fd1a-1f27-a7da-001ec947cd2a")
        @Override
        public SmObjectImpl createImpl() {
            throw new RuntimeException("SmObject is abstract.");
        }

    }

    /**
     * The SmStatus attribute.
     * <p>
     * Records the persistent status.
     * See {@link org.modelio.vcore.smkernel.IPStatus} .
     */
    @objid ("0033e186-702c-1f21-85a5-001ec947cd2a")
    public static class StatusSmAttribute extends SmAttribute {
        @objid ("0033f568-702c-1f21-85a5-001ec947cd2a")
        @Override
        public Object getValue(final ISmObjectData object) {
            final long objStatus = ((SmObjectData) object).status;
            return SmStatus.getPersistentBits(objStatus);
        }

        @objid ("0035d608-702c-1f21-85a5-001ec947cd2a")
        @Override
        public void setValue(final ISmObjectData object, final Object value) {
            assert (value != null);
            long longVal = (long) value;
            
            ((SmObjectData) object).status = SmStatus.setPersistentPart(object.getStatus(), longVal);
        }

    }

}
