/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
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
 * Proxy class to handle a {@link MethodologicalLink} with << Allocated >> stereotype.
 * <p>Stereotype description:
 * <br/><i>null</i></p>
 */
@objid ("c31b12ed-1407-4a23-9634-ab199f21bc98")
public class Allocated {
    @objid ("476d0a51-01b4-48af-a0b2-875cfa5718d9")
    public static final String STEREOTYPE_NAME = "Allocated";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("e91c3108-2252-4871-8dd2-c828a4dfb8d2")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Allocated proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Allocated >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6c803cd6-5852-4ec5-b6fb-fbf292be8a1a")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Allocated >> then instantiate a {@link Allocated} proxy.
     * 
     * @return a {@link Allocated} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("5e3bea58-49c5-405f-ac9a-b3d90dafb064")
    public static Allocated create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Allocated.STEREOTYPE_NAME);
        return Allocated.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Allocated} proxy from a {@link MethodologicalLink} stereotyped << Allocated >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Allocated} proxy or <i>null</i>.
     */
    @objid ("1e84849d-c8c8-4723-b6fe-027259515c9e")
    public static Allocated instantiate(MethodologicalLink obj) {
        return Allocated.canInstantiate(obj) ? new Allocated(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Allocated} proxy from a {@link MethodologicalLink} stereotyped << Allocated >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Allocated} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ed62895a-acc8-4501-8f47-393f003f5dc9")
    public static Allocated safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Allocated.canInstantiate(obj))
        	return new Allocated(obj);
        else
        	throw new IllegalArgumentException("Allocated: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("facd57f5-521d-4f13-afa7-dc1642c0baf9")
    public static ModelElement getTarget(ModelElement source) {
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    @objid ("8aab8462-5dd2-4967-b0a7-08050099b208")
    public static void setTarget(ModelElement source, ModelElement target) {
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
    }

    @objid ("14dea7b8-37c7-443b-b9d7-dddd448c8290")
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
        Allocated other = (Allocated) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}. 
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("ed72fdf6-9135-4c79-a6e9-b586f08b4229")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("7848f928-e225-41d8-97fe-9844aedf1b8a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2437801b-994f-4dfa-83cd-5989ea92db0c")
    protected Allocated(MethodologicalLink elt) {
        this.elt = elt;
    }

    @objid ("4210b4fb-4c64-4c0a-a9eb-127c5a9cf7ab")
    public static final class MdaTypes {
        @objid ("03b2f3ec-90f6-4fb7-a3c8-6e892b745e9e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("7bdaafd7-0169-425d-8527-9e5fb782e086")
        private static Stereotype MDAASSOCDEP;

        @objid ("b57075d1-31e4-43fb-abcf-ad1f7bf569ba")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2b9547d4-926d-49db-834b-d661227dc250")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e5076ee8-b071-4433-a25d-4d8cdddead0a");
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
