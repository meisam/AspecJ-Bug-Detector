/* abc - The AspectBench Compiler
 * Copyright (C) 2007 Reehan Shaikh
 * Copyright (C) 2007 Eric Bodden
 *
 * This compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this compiler, in the file LESSER-GPL;
 * if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

/**
 * October 8, 2008
 */
package mfs.aspectj.findbugs;

import java.util.Collection;

import abc.aspectj.ExtensionInfo;
import abc.weaving.aspectinfo.GlobalAspectInfo;

/**
 * @author Meisam Fathi Salmi as Administrator
 * 
 */
public class AbcExtension extends abc.eaj.AbcExtension {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void collectVersions(final StringBuffer versions) {
		// first delegate up
		super.collectVersions(versions);
		// then add our two cents
		versions.append(" with mfs.findbugs extension "
				+ new Version().toString() + "\n");
	}

	@Override
	public ExtensionInfo makeExtensionInfo(
			final Collection<String> jar_classes,
			final Collection<String> aspect_sources) {
		// This is the code used in the super class
		// return super.makeExtensionInfo(jar_classes, aspect_sources);
		return new mfs.aspectj.findbugs.ExtensionInfo(jar_classes,
				aspect_sources);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see abc.main.AbcExtension#createGlobalAspectInfo()
	 */
	@Override
	protected GlobalAspectInfo createGlobalAspectInfo() {
		return new mfs.aspectj.findbugs.GlobalAspectInfo();
	}

}
