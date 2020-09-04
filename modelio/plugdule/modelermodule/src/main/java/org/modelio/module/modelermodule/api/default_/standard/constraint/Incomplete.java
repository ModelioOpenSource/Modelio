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
 * Proxy class to handle a {@link Constraint} with << incomplete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b8be14f7-5bfd-4614-81be-eb12e3be42a2")
public class Incomplete {
    @objid ("16dd8b51-325b-4748-9b34-271150479658")
    public static final String STEREOTYPE_NAME = "incomplete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("6554cfb0-c3ac-41d4-a4c2-6310944d7965")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Incomplete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << incomplete >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cb17b46d-a124-41eb-96ca-72d16c4582ac")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << incomplete >> then instantiate a {@link Incomplete} proxy.
     * 
     * @return a {@link Incomplete} proxy on the created {@link Constraint}.
     */
    @objid ("36369989-d99a-4038-a71f-74710f706595")
    public static Incomplete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Incomplete.STEREOTYPE_NAME);
        return Incomplete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Incomplete} proxy from a {@link Constraint} stereotyped << incomplete >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Incomplete} proxy or <i>null</i>.
     */
    @objid ("92a2ee17-f46b-422d-afc5-8b43c7aa5733")
    public static Incomplete instantiate(Constraint obj) {
        return Incomplete.canInstantiate(obj) ? new Incomplete(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Incomplete} proxy from a {@link Constraint} stereotyped << incomplete >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Incomplete} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a35916ad-23af-4c07-bfb2-b2e98f4631d6")
    public static Incomplete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Incomplete.canInstantiate(obj))
        	return new Incomplete(obj);
        else
        	throw new IllegalArgumentException("Incomplete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2d715fdf-b9ad-4610-abeb-2f127d7994ef")
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
        Incomplete other = (Incomplete) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("d8d60dbb-5ee8-4fd2-aa2e-9062bde8edd8")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("2bbe7b25-e524-441c-96fb-586350bc27d3")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("87e92150-f463-421f-8934-0f93db401b2e")
    protected Incomplete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("73e28435-6124-4305-92ec-992d4d7fdbe1")
    public static final class MdaTypes {
        @objid ("09744381-b1f5-4904-996b-9a2b59484009")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b0b13253-02e2-4eb4-bae3-c3dea6866f4d")
        private static Stereotype MDAASSOCDEP;

        @objid ("029a23a8-fafd-4e63-995a-43b315bccd28")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4f0cdd84-d345-4b1f-a03d-9e914e596004")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f7-0000-000000000000");
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
