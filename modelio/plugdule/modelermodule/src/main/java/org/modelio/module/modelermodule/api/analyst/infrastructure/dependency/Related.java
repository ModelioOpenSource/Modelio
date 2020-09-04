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
 * Proxy class to handle a {@link Dependency} with << related >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5ac2e06b-dda4-459c-acdb-e71b88f113b8")
public class Related {
    @objid ("e0c6bb4c-8460-4810-a11b-64341b11a185")
    public static final String STEREOTYPE_NAME = "related";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5a867870-f51f-43e4-880e-84d1ce70392c")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Related proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("93e0ae74-b999-4733-a580-a1327edeb1ba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related >> then instantiate a {@link Related} proxy.
     * 
     * @return a {@link Related} proxy on the created {@link Dependency}.
     */
    @objid ("531dd51a-abee-4273-8ce1-7993d5efd762")
    public static Related create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Related.STEREOTYPE_NAME);
        return Related.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Related} proxy or <i>null</i>.
     */
    @objid ("5c0939cd-a291-4ccb-93bb-e48573be030b")
    public static Related instantiate(Dependency obj) {
        return Related.canInstantiate(obj) ? new Related(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Related} proxy from a {@link Dependency} stereotyped << related >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Related} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("8cd50b16-69ea-4dd8-ba66-9397d459264b")
    public static Related safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Related.canInstantiate(obj))
        	return new Related(obj);
        else
        	throw new IllegalArgumentException("Related: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("50248697-1bdc-4c42-9d62-15d4afb3b385")
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
        Related other = (Related) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("2f026948-8cf5-4aa8-944c-e763c67e7e08")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("7ca4d223-2ecf-411b-8634-c2f8986caa2f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0e27f012-a69d-4378-823a-dc7c76925732")
    protected Related(Dependency elt) {
        this.elt = elt;
    }

    @objid ("20b74997-f3b4-4b98-8673-17a6d2577e56")
    public static final class MdaTypes {
        @objid ("2f114b57-a78e-4972-8bee-b11460f2642e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("85a917fb-a6ec-4694-aa89-ab887b2e943c")
        private static Stereotype MDAASSOCDEP;

        @objid ("43fdd677-a579-4986-9c3c-8553b321ba5d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8a1432e3-25c4-40c7-a571-4bffe5e7f13c")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-023d-0000-000000000000");
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
