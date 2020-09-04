/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
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
 * Proxy class to handle a {@link Dependency} with << implement >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("bd3859e6-6a4f-4b1b-82d2-b97c4db33bd4")
public class Implement {
    @objid ("8661dec1-073e-42b8-9918-dcca42107b4c")
    public static final String STEREOTYPE_NAME = "implement";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5e9609f8-75f4-43bf-ba04-8a55d776b608")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Implement proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << implement >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("35dd5476-c91a-42fb-93a0-fd02c00fb598")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << implement >> then instantiate a {@link Implement} proxy.
     * 
     * @return a {@link Implement} proxy on the created {@link Dependency}.
     */
    @objid ("6ecb6498-86ae-4052-9479-0ae9b3dcce85")
    public static Implement create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Implement.STEREOTYPE_NAME);
        return Implement.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Implement} proxy or <i>null</i>.
     */
    @objid ("41da298c-1085-4703-93d5-fb0f8dde919c")
    public static Implement instantiate(Dependency obj) {
        return Implement.canInstantiate(obj) ? new Implement(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Implement} proxy from a {@link Dependency} stereotyped << implement >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Implement} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6dbb0dcd-a952-4f04-8f1b-1755d180d769")
    public static Implement safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Implement.canInstantiate(obj))
        	return new Implement(obj);
        else
        	throw new IllegalArgumentException("Implement: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("08927e5f-67a0-4dca-a8f8-4902b3f3c30d")
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
        Implement other = (Implement) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("06b4313d-39fb-44df-99db-216b81ca6f83")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("3acdb07c-83db-4d01-b9aa-f03c71e14d67")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("1de002b9-20fe-4d65-94fa-416110fa05f0")
    protected Implement(Dependency elt) {
        this.elt = elt;
    }

    @objid ("062103b4-cf74-41a4-8db5-49367f2eaa96")
    public static final class MdaTypes {
        @objid ("8b3ca591-4d88-4523-89bd-df41e4ddabc2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("67a9d1ff-9700-46f3-98df-04d38e57e525")
        private static Stereotype MDAASSOCDEP;

        @objid ("0411301e-ee96-4069-95a3-cfa1640ea27f")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d13fa669-0d6e-4ddd-aa46-75d46d24fafa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0260-0000-000000000000");
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
