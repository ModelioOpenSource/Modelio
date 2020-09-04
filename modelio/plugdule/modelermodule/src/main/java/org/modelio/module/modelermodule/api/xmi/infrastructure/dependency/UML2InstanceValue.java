/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << UML2InstanceValue >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("57682dea-36fc-489f-a20b-7834ce24109f")
public class UML2InstanceValue {
    @objid ("30a01540-7b77-4620-b38a-9f1efb15a32e")
    public static final String STEREOTYPE_NAME = "UML2InstanceValue";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("33aa7414-5f8d-4859-bbb2-b4807981388b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2InstanceValue proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2InstanceValue >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1e483404-1802-4b7f-9f3e-400e425552c1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2InstanceValue >> then instantiate a {@link UML2InstanceValue} proxy.
     * 
     * @return a {@link UML2InstanceValue} proxy on the created {@link Dependency}.
     */
    @objid ("b7459137-a62f-430c-99f4-a5a51106e469")
    public static UML2InstanceValue create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2InstanceValue.STEREOTYPE_NAME);
        return UML2InstanceValue.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2InstanceValue} proxy or <i>null</i>.
     */
    @objid ("447c613b-46ac-4568-a728-d4aa80ee3b0a")
    public static UML2InstanceValue instantiate(Dependency obj) {
        return UML2InstanceValue.canInstantiate(obj) ? new UML2InstanceValue(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2InstanceValue} proxy from a {@link Dependency} stereotyped << UML2InstanceValue >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2InstanceValue} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ce4d3ac8-442d-40c0-a66b-eee28a39ef04")
    public static UML2InstanceValue safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2InstanceValue.canInstantiate(obj))
        	return new UML2InstanceValue(obj);
        else
        	throw new IllegalArgumentException("UML2InstanceValue: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7749d4c4-70bb-421b-8ba1-c64bb684e8f4")
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
        UML2InstanceValue other = (UML2InstanceValue) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("20253d37-6587-45c8-ba9a-07ee5381f2a1")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("85538692-52b4-4443-886e-692a28864f03")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b2eaf826-b06e-4391-9721-bb4eb720ec55")
    protected UML2InstanceValue(Dependency elt) {
        this.elt = elt;
    }

    @objid ("212a0d8e-5958-407c-8c93-a9014079a3cb")
    public static final class MdaTypes {
        @objid ("fd65ee4e-13b9-477e-9f11-4e1bfbbf2124")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8042b516-396b-4d82-a0fc-fb3b15cf18bb")
        private static Stereotype MDAASSOCDEP;

        @objid ("8a443fc3-9363-466e-b7af-eda2545763e2")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("765a39ca-4e1f-4421-8849-1ee31736f1ba")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "5791cd76-03ec-11e2-9c63-0027103f347d");
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
