/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << frameWork >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("8c7e5fca-e81e-401f-92b7-8980e98e3dd1")
public class FrameWork {
    @objid ("2ccaa121-4c36-4bb5-b1a5-b63656e427b4")
    public static final String STEREOTYPE_NAME = "frameWork";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("6e3a01fb-e00a-4f0d-8fed-ca902dbb3e66")
    protected final Package elt;

    /**
     * Tells whether a {@link FrameWork proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << frameWork >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c721027b-30d1-4fbc-b293-1dabba09dc58")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << frameWork >> then instantiate a {@link FrameWork} proxy.
     * 
     * @return a {@link FrameWork} proxy on the created {@link Package}.
     */
    @objid ("c8c121c4-53ed-4030-bba7-d4b1db3b211c")
    public static FrameWork create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, FrameWork.STEREOTYPE_NAME);
        return FrameWork.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link FrameWork} proxy or <i>null</i>.
     */
    @objid ("76f5a6f2-56e8-427b-8bff-9abcdc735d5a")
    public static FrameWork instantiate(Package obj) {
        return FrameWork.canInstantiate(obj) ? new FrameWork(obj) : null;
    }

    /**
     * Tries to instantiate a {@link FrameWork} proxy from a {@link Package} stereotyped << frameWork >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link FrameWork} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5c1a0e98-d710-46c9-8113-0ce6908cca04")
    public static FrameWork safeInstantiate(Package obj) throws IllegalArgumentException {
        if (FrameWork.canInstantiate(obj))
        	return new FrameWork(obj);
        else
        	throw new IllegalArgumentException("FrameWork: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3c88eb5e-1ebf-4a5d-82e4-fd7d0817aa28")
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
        FrameWork other = (FrameWork) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("cfd9e957-62e3-463a-8914-268237781a95")
    public Package getElement() {
        return this.elt;
    }

    @objid ("9cade1d3-a404-4bad-ab09-8197995d3a5f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("4ee60793-9a64-4b3a-b953-8d3e184a9b61")
    protected FrameWork(Package elt) {
        this.elt = elt;
    }

    @objid ("c823ec2f-0b36-488c-9bc7-287eaac9b7cf")
    public static final class MdaTypes {
        @objid ("ba7abe85-8e83-4a76-b27b-8fcb48481a81")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4a8037c5-405a-432f-8553-e34d053ef9a7")
        private static Stereotype MDAASSOCDEP;

        @objid ("22f022af-1644-4b3a-9516-d115262a47ff")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0f09373f-785c-43c3-988f-e360a681dec6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01db-0000-000000000000");
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
