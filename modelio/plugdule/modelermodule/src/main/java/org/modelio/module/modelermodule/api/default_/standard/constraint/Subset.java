/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Constraint} with << subset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("64070ac9-b416-414f-8cd6-63da9a9124aa")
public class Subset {
    @objid ("54230071-7b0f-4fd0-a187-77fcce114f46")
    public static final String STEREOTYPE_NAME = "subset";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("80c908c3-605c-47ee-b23f-7038ce8d85dc")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Subset proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << subset >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("32ccee3e-909e-4638-892a-30ab89816602")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << subset >> then instantiate a {@link Subset} proxy.
     * 
     * @return a {@link Subset} proxy on the created {@link Constraint}.
     */
    @objid ("a02a6257-8ed2-4ee3-a20d-4c29c7c9e96a")
    public static Subset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Subset.STEREOTYPE_NAME);
        return Subset.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Subset} proxy or <i>null</i>.
     */
    @objid ("a2c49929-e225-4194-9138-2ffee2df743b")
    public static Subset instantiate(Constraint obj) {
        return Subset.canInstantiate(obj) ? new Subset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Subset} proxy from a {@link Constraint} stereotyped << subset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Subset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("fa9d6358-7d30-4cf2-9a12-4d4462e87ab1")
    public static Subset safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Subset.canInstantiate(obj))
        	return new Subset(obj);
        else
        	throw new IllegalArgumentException("Subset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7ae890af-0e4f-4351-bf9f-273076f8749e")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Subset other = (Subset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("e5decc55-22d8-4648-9f4d-54b41a1c0ffd")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("af04edf0-9516-4199-8be5-605e609fa08e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c0f89a03-52f1-41b7-8179-67ac6dc9e244")
    protected Subset(Constraint elt) {
        this.elt = elt;
    }

    @objid ("b023fad9-6eb0-417d-83a0-1258b9a55847")
    public static final class MdaTypes {
        @objid ("5bb5484a-2c50-4370-a783-db22f64ea0e8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1cfcc632-b13f-4736-a2fc-8c4212f0b8a9")
        private static Stereotype MDAASSOCDEP;

        @objid ("45046d31-dc58-4470-b42c-eea696b37017")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("19b1e6a2-143c-479a-a870-a04a0b7be697")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0005-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
