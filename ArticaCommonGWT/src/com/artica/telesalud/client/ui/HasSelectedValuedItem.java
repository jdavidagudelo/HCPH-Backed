/**
 *   
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   MvpExample is an example guide to adopt the MVP Pattern
 *   Copyright (C) 2011  David López - coolcoder.ex@gmail.com    
*/

package com.artica.telesalud.client.ui;

import java.util.List;

import com.artica.telesalud.client.shared.ValuedItem;
import com.google.gwt.user.client.ui.HasValue;

public interface HasSelectedValuedItem<K> extends HasValue<K> {
	
	void setOptions(List<ValuedItem<K, String>> options);

	void setSelectedValue(K selected);

	K getSelectedValue();
}
