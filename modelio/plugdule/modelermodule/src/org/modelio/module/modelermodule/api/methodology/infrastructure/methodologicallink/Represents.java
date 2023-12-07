/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.methodology.infrastructure.methodologicallink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.uml.infrastructure.MethodologicalLink;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link MethodologicalLink} with << Represents >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Business Objects, Data Objects, Products, Artifacts, doivent par drag & drop créer des Data Objects BPMN associés.</i></p>
 */
@objid ("f455da76-7d21-4a23-86cb-44284ad9c018")
public class Represents {
    @objid ("c7c1debb-4c5a-4b2f-88e9-32501f718bfa")
    public static final String STEREOTYPE_NAME = "Represents";

    /**
     * The underlying {@link MethodologicalLink} represented by this proxy, never null.
     */
    @objid ("06015792-11de-4762-82e3-237b7329d3f3")
    protected final MethodologicalLink elt;

    /**
     * Tells whether a {@link Represents proxy} can be instantiated from a {@link MObject} checking it is a {@link MethodologicalLink} stereotyped << Represents >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("cbb6a7fa-d433-4a9f-9d28-02bd4ae85438")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MethodologicalLink) && ((MethodologicalLink) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MethodologicalLink} stereotyped << Represents >> then instantiate a {@link Represents} proxy.
     * @return a {@link Represents} proxy on the created {@link MethodologicalLink}.
     */
    @objid ("32339d7b-7cae-4011-92de-0efbe9dbb5ed")
    public static Represents create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MethodologicalLink");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Represents.STEREOTYPE_NAME);
        return Represents.instantiate((MethodologicalLink)e);
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >> checking its metaclass and its stereotype.
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MethodologicalLink
     * @return a {@link Represents} proxy or <i>null</i>.
     */
    @objid ("4aee9d5c-c7a8-411e-ac32-35b6a617957a")
    public static Represents instantiate(MethodologicalLink obj) {
        return Represents.canInstantiate(obj) ? new Represents(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Represents} proxy from a {@link MethodologicalLink} stereotyped << Represents >> checking its metaclass and its stereotype.
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MethodologicalLink}
     * @return a {@link Represents} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ca34eb2d-da62-46e9-8626-fdfe9f1bce71")
    public static Represents safeInstantiate(MethodologicalLink obj) throws IllegalArgumentException {
        if (Represents.canInstantiate(obj))
            return new Represents(obj);
        else
            throw new IllegalArgumentException("Represents: Cannot instantiate "+obj+": wrong element type or stereotype");
        
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("b68488da-cf0c-4fc1-94b3-9950d52ae9bb")
    public static ModelElement getTarget(ModelElement source) {
        preloadStereotype(source);
        return AbstractMethodologicalLink.getTarget(source, MdaTypes.STEREOTYPE_ELT);
    }

    /**
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     */
    @objid ("85a28a48-571f-47ac-b1b3-7f5fb1f27c90")
    public static void setTarget(ModelElement source, ModelElement target) {
        preloadStereotype(source);
        
        AbstractMethodologicalLink.setTarget(source, MdaTypes.STEREOTYPE_ELT, target);
        
    }

    @objid ("28348dd3-b54a-4dfd-a94b-182de139d1da")
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
        Represents other = (Represents) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MethodologicalLink}.
     * @return the MethodologicalLink represented by this proxy, never null.
     */
    @objid ("87570b45-03bf-459b-8340-6ec23eba1bef")
    public MethodologicalLink getElement() {
        return this.elt;
    }

    @objid ("be1a60f1-2ad7-427b-879f-3174807a7d6e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("221886f8-93e7-4dfd-a672-5935b1737418")
    protected  Represents(MethodologicalLink elt) {
        this.elt = elt;
    }

    /**
     * Ensure {@link MdaTypes#STEREOTYPE_ELT} is loaded with an element.
     * <p>
     * {@link MdaTypes#STEREOTYPE_ELT} may be null when called while doing metamodel migration on model fragments.
     * In this case modules are not yet loaded and MDA proxies are not initialized.
     * 
     * WARNING: Manual method. Do not use ModelioStudio 2.0.xx API generator on ModelerModule otherwise the method will be cancelled. Need an evolution od ModelioStudio.
     * @param source a model element to guess the {@link CoreSession} .
     * @since 5.4.1 25/10/2023
     */
    @objid ("a1827fcf-f98b-4c4c-9474-b7d35680ea9b")
    private static void preloadStereotype(ModelElement source) {
        if (MdaTypes.STEREOTYPE_ELT == null) {
            CoreSession session = CoreSession.getSession(source);
            // Note : with this we always have an element, shell if the module is missing.
            MdaTypes.STEREOTYPE_ELT = (Stereotype) session.getSmFactory().getObjectReference(
                    session.getMetamodel().getMClass(Stereotype.class),
                    "f5d2927d-46d6-4d87-9cf2-adb4a47ca929",
                    STEREOTYPE_NAME);
        }
        
    }

    @objid ("d9896322-b59f-496b-99be-d10d51513d32")
    public static final class MdaTypes {
        @objid ("0d8ff022-2d1f-47f1-9f06-b8e8705562f1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d31a0d56-aa7b-4229-abdb-67eaabb50a2e")
        private static Stereotype MDAASSOCDEP;

        @objid ("c538d3a5-2af9-4f68-a7a5-f6c629078818")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c2322f2c-d503-4ada-b1d4-09396ef125d8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "f5d2927d-46d6-4d87-9cf2-adb4a47ca929");
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
