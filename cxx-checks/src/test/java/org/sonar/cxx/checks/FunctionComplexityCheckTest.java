/*
 * Sonar C++ Plugin (Community)
 * Copyright (C) 2010-2017 SonarOpenCommunity
 * http://github.com/SonarOpenCommunity/sonar-cxx
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.cxx.checks;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.sonar.cxx.CxxAstScanner;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

public class FunctionComplexityCheckTest {

  @Test
  public void check() throws UnsupportedEncodingException, IOException {
    FunctionComplexityCheck check = new FunctionComplexityCheck();
    check.setMax(5);
    CxxFileTester tester = CxxFileTesterHelper.CreateCxxFileTester("src/test/resources/checks/FunctionComplexity.cc", ".");      
    SourceFile file = CxxAstScanner.scanSingleFile(tester.cxxFile, tester.sensorContext, CxxFileTesterHelper.mockCxxLanguage(), check);    
    
    CheckMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(13)
      .next().atLine(33)
      .next().atLine(51)
      .next().atLine(72);
  }

}
