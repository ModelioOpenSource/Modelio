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

package org.modelio.metamodel.impl.mmextensions.standard.modelshield;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E200Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E201Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E202Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E203Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E204Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E205Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E206Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E207Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E208Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E209Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E210Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E211Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E212Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E213Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E214Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E215Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E216Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E217Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E225Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E226Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E227Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E228Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E229Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E230Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E231Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E232Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E233Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E234Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E235Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E236Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E237Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E238Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E240Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E241Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E242Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E243Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E244Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E245Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E246Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E247Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E248Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E249Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E250Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E251Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E252Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E253Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E254Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E255Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E256Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E257Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E258Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E259Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E260Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E261Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E262Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E263Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E264Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E265Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E266Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E267Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E268Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E269Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E270Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E271Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E272Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E273Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E274Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E275Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E276Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E277Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E278Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E279Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E280Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E282Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E283Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E293Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E294Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E295Checker;
import org.modelio.metamodel.impl.mmextensions.standard.modelshield.standardcheckers.E296Checker;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.modelshield.api.IModelShieldRegistry;
import org.modelio.vcore.smkernel.mapi.modelshield.spi.ICheckerFactory;

@objid ("ac5c1fb5-df68-4c34-b6e0-64dfc7fc7422")
public class StandardCheckerFactory implements ICheckerFactory {
    /**
     * Create and register the model shield checkers.
     * 
     * @param plan the model shield registry where checkers must be registered.
     * @param metamodel the current metamodel.
     */
    @objid ("84f4ccb5-ac80-4619-ac25-99c8723ff407")
    @Override
    public void createCheckers(final IModelShieldRegistry plan, MMetamodel metamodel) {
        new E200Checker().register(plan,metamodel);
        new E201Checker().register(plan,metamodel);
        new E202Checker().register(plan,metamodel);
        new E203Checker().register(plan,metamodel);
        new E204Checker().register(plan,metamodel);
        new E205Checker().register(plan,metamodel);
        new E206Checker().register(plan,metamodel);
        new E207Checker().register(plan,metamodel);
        new E208Checker().register(plan,metamodel);
        new E209Checker().register(plan,metamodel);
        new E210Checker().register(plan,metamodel);
        new E211Checker().register(plan,metamodel);
        new E212Checker().register(plan,metamodel);
        new E213Checker().register(plan,metamodel);
        new E214Checker().register(plan,metamodel);
        new E215Checker().register(plan,metamodel);
        new E216Checker().register(plan,metamodel);
        new E217Checker().register(plan,metamodel);
        new E225Checker().register(plan,metamodel);
        new E226Checker().register(plan,metamodel);
        new E227Checker().register(plan,metamodel);
        new E228Checker().register(plan,metamodel);
        new E229Checker().register(plan,metamodel);
        new E230Checker().register(plan,metamodel);
        new E231Checker().register(plan,metamodel);
        new E232Checker().register(plan,metamodel);
        new E233Checker().register(plan,metamodel);
        new E234Checker().register(plan,metamodel);
        new E235Checker().register(plan,metamodel);
        new E236Checker().register(plan,metamodel);
        new E237Checker().register(plan,metamodel);
        new E238Checker().register(plan,metamodel);
        new E240Checker().register(plan,metamodel);
        new E241Checker().register(plan,metamodel);
        new E242Checker().register(plan,metamodel);
        new E243Checker().register(plan,metamodel);
        new E244Checker().register(plan,metamodel);
        new E245Checker().register(plan,metamodel);
        new E246Checker().register(plan,metamodel);
        new E247Checker().register(plan,metamodel);
        new E248Checker().register(plan,metamodel);
        new E249Checker().register(plan,metamodel);
        new E250Checker().register(plan,metamodel);
        new E251Checker().register(plan,metamodel);
        new E252Checker().register(plan,metamodel);
        new E253Checker().register(plan,metamodel);
        new E254Checker().register(plan,metamodel);
        new E255Checker().register(plan,metamodel);
        new E256Checker().register(plan,metamodel);
        new E257Checker().register(plan,metamodel);
        new E258Checker().register(plan,metamodel);
        new E259Checker().register(plan,metamodel);
        new E260Checker().register(plan,metamodel);
        new E261Checker().register(plan,metamodel);
        new E262Checker().register(plan,metamodel);
        new E263Checker().register(plan,metamodel);
        new E264Checker().register(plan,metamodel);
        new E265Checker().register(plan,metamodel);
        new E266Checker().register(plan,metamodel);
        new E267Checker().register(plan,metamodel);
        new E268Checker().register(plan,metamodel);
        new E269Checker().register(plan,metamodel);
        new E270Checker().register(plan,metamodel);
        new E271Checker().register(plan,metamodel);
        new E272Checker().register(plan,metamodel);
        new E273Checker().register(plan,metamodel);
        new E274Checker().register(plan,metamodel);
        new E275Checker().register(plan,metamodel);
        new E276Checker().register(plan,metamodel);
        new E277Checker().register(plan,metamodel);
        new E278Checker().register(plan,metamodel);
        new E279Checker().register(plan,metamodel);
        new E280Checker().register(plan,metamodel);
        new E282Checker().register(plan,metamodel);
        new E283Checker().register(plan,metamodel);
        new E293Checker().register(plan,metamodel);
        new E294Checker().register(plan,metamodel);
        new E295Checker().register(plan,metamodel);
        new E296Checker().register(plan,metamodel);
    }

}
