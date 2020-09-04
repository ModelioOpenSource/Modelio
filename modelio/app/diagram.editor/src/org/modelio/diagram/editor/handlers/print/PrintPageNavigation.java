/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.handlers.print;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * @author apedro
 * 
 * Allows to navigate on pages (for printing) with x and y coordinates
 * x and y starts at 1
 */
@objid ("65b6dcf0-33f7-11e2-95fe-001ec947c8cc")
public class PrintPageNavigation {
    /**
     * Position of page on horizontal coordinates
     */
    @objid ("65b6dcf2-33f7-11e2-95fe-001ec947c8cc")
    public int x = 1;

    /**
     * Position of page on vertical coordinates
     */
    @objid ("65b6dcf4-33f7-11e2-95fe-001ec947c8cc")
    public int y = 1;

    @objid ("65b6dcf6-33f7-11e2-95fe-001ec947c8cc")
    private int nbPagesX = 1;

    @objid ("65b6dcf7-33f7-11e2-95fe-001ec947c8cc")
    private int nbPagesY = 1;

    /**
     * @return
     * Number of pages on horizontal coordinates
     */
    @objid ("65b6dcf8-33f7-11e2-95fe-001ec947c8cc")
    public int getNbPagesX() {
        return this.nbPagesX;
    }

    @objid ("65b6dcfd-33f7-11e2-95fe-001ec947c8cc")
    public void setNbPagesX(int nbPagesX) {
        this.nbPagesX = nbPagesX;
    }

    /**
     * @return
     * Number of pages on vertical coordinates
     */
    @objid ("65b6dd00-33f7-11e2-95fe-001ec947c8cc")
    public int getNbPagesY() {
        return this.nbPagesY;
    }

    @objid ("65b93f2c-33f7-11e2-95fe-001ec947c8cc")
    public void setNbPagesY(int nbPagesY) {
        this.nbPagesY = nbPagesY;
    }

    @objid ("65b93f2f-33f7-11e2-95fe-001ec947c8cc")
    public PrintPageNavigation(int nbPagesX, int nbPagesY) {
        this.nbPagesX = nbPagesX;
        this.nbPagesY = nbPagesY;
    }

    @objid ("65b93f33-33f7-11e2-95fe-001ec947c8cc")
    public boolean isLastPage() {
        if (this.x == this.nbPagesX && this.y == this.nbPagesY)
            return true;
        else
            return false;
    }

    @objid ("65b93f36-33f7-11e2-95fe-001ec947c8cc")
    public boolean isFirstPage() {
        if (this.x == 1 && this.y == 1)
            return true;
        else
            return false;
    }

    /**
     * Browse to the next page
     */
    @objid ("65b93f39-33f7-11e2-95fe-001ec947c8cc")
    public void goNextPage() {
        if (isLastPage())
            return;
        else {
            if (this.x < this.nbPagesX)
                this.x = this.x + 1;
            else if (this.x == this.nbPagesX) {
                this.x = 1;
                if (this.y < this.nbPagesY)
                    this.y = this.y + 1;
            }
        }
    }

    /**
     * Browse to the previous page
     */
    @objid ("65b93f3c-33f7-11e2-95fe-001ec947c8cc")
    public void goPreviousPage() {
        if (isFirstPage())
            return;
        else {
            if (this.x > 1)
                this.x = this.x - 1;
            else if (this.x == 1) {             
                if (this.y > 1) {
                    this.y = this.y - 1;
                    this.x = this.nbPagesX;
                }
            }
        }
    }

    @objid ("65b93f3f-33f7-11e2-95fe-001ec947c8cc")
    public int getCurrentPageNumber() {
        int currentPageNumber = (this.y - 1)*this.nbPagesX + this.x;
        return currentPageNumber;
    }

    @objid ("65b93f43-33f7-11e2-95fe-001ec947c8cc")
    public int getTotalPages() {
        return (this.nbPagesX * this.nbPagesY);
    }

}
