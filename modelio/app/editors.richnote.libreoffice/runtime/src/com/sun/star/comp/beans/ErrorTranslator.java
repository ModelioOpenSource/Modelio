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

package com.sun.star.comp.beans;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.task.ErrorCodeIOException;
import org.modelio.editors.richnote.libreoffice.plugin.LibreOfficeEditors;

/**
 * OpenOffice error code translator.
 * <p>
 * Use {@link #getErrorMessage(ErrorCodeIOException)} to get the message of an ErrorCodeIOException.
 * <p>
 * Taken from <i>libreoffice/core/plain/tools/inc/tools/errcode.hxx</i> source file.
 * <p>
 * @see <a href="http://cgit.freedesktop.org/libreoffice/core/plain/tools/inc/tools/errcode.hxx">errcode.hxx</a>
 * @see <a href="http://cgit.freedesktop.org/libreoffice/core/tree/svtools/inc/svtools/sfxecode.hxx">sfxecode.hxx</a>
 * @see <a href="http://cgit.freedesktop.org/libreoffice/core/tree/svtools/inc/svtools/soerr.hxx">soerr.hxx</a>
 * 
 * 
 * @author cmarin
 */
@objid ("c301dd9f-221c-4ace-948b-493e6ab88f1b")
public class ErrorTranslator {
    @objid ("8101fb05-0aff-4872-b2d1-ed90ff7f99db")
     static final long ERRCODE_BUTTON_OK = 0x01;

    @objid ("3d8e2ebc-df89-4a8f-b7ec-35c377d6e87f")
     static final long ERRCODE_BUTTON_CANCEL = 0x02;

    @objid ("49eb4b72-b644-44df-b376-416684a6c65e")
     static final long ERRCODE_BUTTON_RETRY = 0x04;

    @objid ("69837433-95a5-49d3-8eb5-969ef7fbf216")
     static final long ERRCODE_BUTTON_OK_CANCEL = 0x03;

    @objid ("62dcbc72-17c8-4911-b962-93883bbbdf4f")
     static final long ERRCODE_BUTTON_OK_RETRY_CANCEL = 0x07;

    @objid ("a86cf1c4-53ef-4a32-98ca-e8b07062301a")
     static final long ERRCODE_BUTTON_NO = 0x08;

    @objid ("4ff4cfe4-e81f-42c2-bad8-df56fe3c056b")
     static final long ERRCODE_BUTTON_YES = 0x10;

    @objid ("9cd89a7a-cd16-4293-8143-136713373756")
     static final long ERRCODE_BUTTON_YES_NO = 0x18;

    @objid ("3f4b6f40-85fb-4014-9f68-9668b9fdf3c8")
     static final long ERRCODE_BUTTON_YES_NO_CANCEL = 0x1a;

    @objid ("f0860a2c-a47f-4647-80e8-413d9d206b86")
     static final long ERRCODE_BUTTON_DEF_OK = 0x100;

    @objid ("e0b2520c-82ad-4537-8fe5-bf318325e929")
     static final long ERRCODE_BUTTON_DEF_CANCEL = 0x200;

    @objid ("3a111586-1b32-44f2-8e9d-1e9e511638bc")
     static final long ERRCODE_BUTTON_DEF_YES = 0x300;

    @objid ("4e6af4a1-cf6d-4303-9d6a-1a2faaac32ab")
     static final long ERRCODE_BUTTON_DEF_NO = 0x400;

    @objid ("ca3eec62-5144-49eb-a1a9-461506a33d18")
     static final long ERRCODE_MSG_ERROR = 0x1000;

    @objid ("079b21b7-ed39-459d-8b0f-e201d5bfa0cc")
     static final long ERRCODE_MSG_WARNING = 0x2000;

    @objid ("0cb8f9a8-4cb2-45ac-a68e-4216fec24930")
     static final long ERRCODE_MSG_INFO = 0x3000;

    @objid ("4e6965bd-33d3-46ec-bc3d-206840b49bb1")
     static final long ERRCODE_MSG_QUERY = 0x4000;

    @objid ("8dc77bbc-c29e-4c68-b147-ddd33393fc27")
     static final long ERRCODE_ERROR_MASK = 0x3fffffff;

    @objid ("59574caa-c33b-4e28-a388-ee5aae85ec00")
     static final long ERRCODE_WARNING_MASK = 0x80000000;

    @objid ("12e4ec1d-983e-4968-9180-36bfa9469b97")
     static final long ERRCODE_RES_MASK = 0x7fff;

    @objid ("ab208024-1cda-4dd6-aeb7-9076b96409e9")
     static final long ERRCODE_CLASS_SHIFT = 8;

    @objid ("2b7b2e37-b26c-4197-843c-5fccb34259dd")
     static final long ERRCODE_AREA_SHIFT = 13;

    @objid ("ecf8c0c3-ef0b-4542-856e-a27add273770")
     static final long ERRCODE_DYNAMIC_SHIFT = 26;

    @objid ("53d0c87d-78fd-41f4-ae01-03e9a1327367")
     static final long ERRCODE_CLASS_MASK = (31 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("0c5f6fa6-2eda-41a5-88aa-8fe81fa98611")
     static final long ERRCODE_DYNAMIC_START = (1 << ErrorTranslator.ERRCODE_DYNAMIC_SHIFT);

    @objid ("65401122-72f4-45e4-926b-3a3d5ee2ef47")
     static final long ERRCODE_DYNAMIC_COUNT = 31;

    @objid ("ef851503-606a-4ba3-867a-7cdfea5b407f")
     static final long ERRCODE_DYNAMIC_MASK = (31 << ErrorTranslator.ERRCODE_DYNAMIC_SHIFT);

    @objid ("fc5db9c7-c986-4c83-bee9-9b27aa604581")
     static final long ERRCODE_AREA_TOOLS = (0 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("0e390029-5ba6-473a-b2c0-1ce05cf1ab10")
     static final long ERRCODE_AREA_SV = (1 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("df3991df-a109-4a04-98a4-92321ce44b38")
     static final long ERRCODE_AREA_SFX = (2 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("bb64f12e-64af-4ef7-8418-9f2b5a138910")
     static final long ERRCODE_AREA_INET = (3 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("300b4642-b60b-4377-a03f-23c4c0b09a91")
     static final long ERRCODE_AREA_IO = ErrorTranslator.ERRCODE_AREA_TOOLS;

    @objid ("da1e0923-bd8d-4f4b-8e23-00295d5aa3a6")
     static final long ERRCODE_AREA_LIB1 = (8 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("80b86705-dfe3-4530-852c-13fa9e738b12")
     static final long ERRCODE_AREA_SVX = ErrorTranslator.ERRCODE_AREA_LIB1;

    @objid ("941463dc-e009-4b32-8453-8ea40fce8c3c")
     static final long ERRCODE_AREA_SO = (9 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("7f4f879c-8978-4c6b-ba27-48fa0372cc24")
     static final long ERRCODE_AREA_SBX = (10 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("7dc289b5-4a0c-4e41-b2b5-a82dabafe6e7")
     static final long ERRCODE_AREA_SBX_END = ((11 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("80f7ccf9-394d-4e0c-8c29-be69b58a6ef0")
     static final long ERRCODE_AREA_DB = (11 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("5b49cb0d-2b68-4dc9-960a-82539a7198b3")
     static final long ERRCODE_AREA_DB_END = ((12 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("ec7a1788-07fd-44ed-b69c-d30412f36c33")
     static final long ERRCODE_AREA_JAVA = (12 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("b1fda7e0-95eb-45de-ac8e-aa74a9b7dae6")
     static final long ERRCODE_AREA_JAVA_END = ((13 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("70943f24-295e-455f-bec0-86db1a5c86d0")
     static final long ERRCODE_AREA_UUI = (13 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("1f623e32-a14d-4bf0-8d19-d36b4f3fda5d")
     static final long ERRCODE_AREA_UUI_END = ((14 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("e766a11a-a4d2-433a-a203-45d2002d2901")
     static final long ERRCODE_AREA_LIB2 = (14 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("7018c95e-a6d6-4e95-863e-d290a7dce311")
     static final long ERRCODE_AREA_LIB2_END = ((15 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("f9a1d46c-b53b-4c6f-9ea4-511084ac0bc7")
     static final long ERRCODE_AREA_CHAOS = (15 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("ed33f700-cece-451e-a566-69823bfc063b")
     static final long ERRCODE_AREA_CHAOS_END = ((16 << ErrorTranslator.ERRCODE_AREA_SHIFT) - 1);

    @objid ("4b9ae0b0-5a7b-4d62-b568-534bde179a60")
     static final long ERRCODE_AREA_SO_END = (ErrorTranslator.ERRCODE_AREA_SBX - 1);

    @objid ("bda5afd3-8769-4a80-bb16-f995bf4a6373")
     static final long ERRCODE_AREA_SVX_END = (ErrorTranslator.ERRCODE_AREA_SO - 1);

    @objid ("aad6bb88-2c20-485c-a9aa-4b620fcae40d")
     static final long ERRCODE_AREA_APP1 = (32 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("a977d093-1b9a-44a2-91c3-54e5b889287e")
     static final long ERRCODE_AREA_APP2 = (40 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("3eb96f19-dc8b-4582-8875-bcee2fa08f37")
     static final long ERRCODE_AREA_APP3 = (48 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("410328fb-6844-428c-9c0d-f523b0727e01")
     static final long ERRCODE_AREA_APP4 = (56 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("42586685-f859-4413-a745-6aed6e41df6b")
     static final long ERRCODE_AREA_APP5 = (64 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("5379cf1e-f112-4354-a34a-027975e33b72")
     static final long ERRCODE_AREA_APP6 = (72 << ErrorTranslator.ERRCODE_AREA_SHIFT);

    @objid ("2935537e-b8bd-47ff-9911-c827b3fa04ce")
     static final long ERRCODE_AREA_SC = ErrorTranslator.ERRCODE_AREA_APP1;

    @objid ("391ce1bc-b7b1-4588-ac15-9cfd4921d452")
     static final long ERRCODE_AREA_SC_END = (ErrorTranslator.ERRCODE_AREA_APP2 - 1);

    @objid ("90e9123f-25b1-46b8-9338-bee4e8d55cad")
     static final long ERRCODE_AREA_SD = ErrorTranslator.ERRCODE_AREA_APP2;

    @objid ("756e7a41-62fc-48ba-9851-8495854a1d45")
     static final long ERRCODE_AREA_SD_END = (ErrorTranslator.ERRCODE_AREA_APP3 - 1);

    @objid ("2996969c-68fa-44eb-8494-f066bd0ece57")
     static final long ERRCODE_AREA_SW = ErrorTranslator.ERRCODE_AREA_APP4;

    @objid ("29f48fce-8147-4eca-b2ee-c7045c62809f")
     static final long ERRCODE_AREA_SW_END = (ErrorTranslator.ERRCODE_AREA_APP5 - 1);

    @objid ("c2fbdfeb-d229-4c77-a568-70f044e00e4f")
     static final long ERRCODE_AREA_OFA = ErrorTranslator.ERRCODE_AREA_APP5;

    @objid ("507b0b4e-6215-452c-926f-df7a82635693")
     static final long ERRCODE_AREA_OFA_END = (ErrorTranslator.ERRCODE_AREA_APP6 - 1);

    @objid ("b79381d4-cc91-494f-aeb9-a2d8409bf284")
     static final long ERRCODE_CLASS_NONE = (0 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("4963e521-7ee3-4e7a-bbd6-33b9d6ac2226")
     static final long ERRCODE_CLASS_ABORT = (1 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("96151b07-ef3f-4ede-ada6-21dd4c0aabed")
     static final long ERRCODE_CLASS_GENERAL = (2 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("d35d672a-a31f-4e06-b661-4d99a5102c77")
     static final long ERRCODE_CLASS_NOTEXISTS = (3 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("72359be7-373f-42ed-82c0-e37bde89459f")
     static final long ERRCODE_CLASS_ALREADYEXISTS = (4 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("de0f6bf3-8a7c-4e3e-a47b-1a1b5110dd1f")
     static final long ERRCODE_CLASS_ACCESS = (5 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("31b918eb-08fd-4308-a8f3-bb9497bc034c")
     static final long ERRCODE_CLASS_PATH = (6 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("54fe2b27-01c6-4a30-a5ac-c2eb4a681577")
     static final long ERRCODE_CLASS_LOCKING = (7 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("87ca8ca4-4e3c-4f55-92de-c22e69cbf02f")
     static final long ERRCODE_CLASS_PARAMETER = (8 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("ddd6a19a-b996-4b06-80be-f9676ef1a287")
     static final long ERRCODE_CLASS_SPACE = (9 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("b1d8597a-15e8-4ec3-a34c-dab00d17d9e4")
     static final long ERRCODE_CLASS_NOTSUPPORTED = (10 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("2e7244aa-ebdc-4c98-82ee-fab2e8f24234")
     static final long ERRCODE_CLASS_READ = (11 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("30eac715-2f55-48a5-8769-1d2cd0036b16")
     static final long ERRCODE_CLASS_WRITE = (12 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("3c5d2725-d412-4b57-b1ca-64415fbadca3")
     static final long ERRCODE_CLASS_UNKNOWN = (13 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("e91e2fe0-5944-4a0e-b3cf-43f4bf2e52c1")
     static final long ERRCODE_CLASS_VERSION = (14 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("55d468d3-50f2-4288-bbc6-86e6a0c87eae")
     static final long ERRCODE_CLASS_FORMAT = (15 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("cfe48560-90dc-47bc-ae3c-257d9f5e38c4")
     static final long ERRCODE_CLASS_CREATE = (16 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("47366843-c974-4b96-86a3-32468aa09c02")
     static final long ERRCODE_CLASS_IMPORT = (17 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("fea1d43d-03b0-466a-a5c3-0c88bad511db")
     static final long ERRCODE_CLASS_EXPORT = (18 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("87fc83d5-7f9e-48c0-838e-9fab11579ea6")
     static final long ERRCODE_CLASS_FILTER = (19 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("4f0b1675-6d26-4977-8aad-48ef1d587087")
     static final long ERRCODE_CLASS_SO = (20 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("bdccfa00-95f1-471f-9935-1c72c4930d25")
     static final long ERRCODE_CLASS_SBX = (21 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("e059f528-4d0b-4e56-b23a-d36689659d3e")
     static final long ERRCODE_CLASS_RUNTIME = (22 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("d4ac20a7-f7a0-4262-9e4c-a95ec0cb80a4")
     static final long ERRCODE_CLASS_COMPILER = (23 << ErrorTranslator.ERRCODE_CLASS_SHIFT);

    @objid ("61807c61-d890-41cc-abeb-7d6b822d5e6c")
     static final long ERRCODE_IO_MISPLACEDCHAR = (1 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("83db84c2-abba-4f7f-8f40-bba83d9b0ae5")
     static final long ERRCODE_IO_NOTEXISTS = (2 | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("ae8056db-bf51-48c7-9409-76140e8b8f3d")
     static final long ERRCODE_IO_ALREADYEXISTS = (3 | ErrorTranslator.ERRCODE_CLASS_ALREADYEXISTS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("9c58bf29-e042-498a-8405-af62a6465dc4")
     static final long ERRCODE_IO_NOTADIRECTORY = (4 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("00106728-e1b0-4c4b-9d3c-9cb02488df87")
     static final long ERRCODE_IO_NOTAFILE = (5 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("d9b6a533-594c-4bae-943e-17b33583199f")
     static final long ERRCODE_IO_INVALIDDEVICE = (6 | ErrorTranslator.ERRCODE_CLASS_PATH | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("cf64de5d-b7f6-4631-b6a2-e30e542a350b")
     static final long ERRCODE_IO_ACCESSDENIED = (7 | ErrorTranslator.ERRCODE_CLASS_ACCESS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("d8b28f9b-2bca-441f-82b6-e9807092537e")
     static final long ERRCODE_IO_LOCKVIOLATION = (8 | ErrorTranslator.ERRCODE_CLASS_LOCKING | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("293609d4-f4bb-4f04-9501-66f8a1b95590")
     static final long ERRCODE_IO_OUTOFSPACE = (9 | ErrorTranslator.ERRCODE_CLASS_SPACE | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("7e36db88-b347-48c2-a207-f6b3a012db25")
     static final long ERRCODE_IO_ISWILDCARD = (11 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("13fbe24e-9db5-463d-9e71-668b8f26e75d")
     static final long ERRCODE_IO_NOTSUPPORTED = (12 | ErrorTranslator.ERRCODE_CLASS_NOTSUPPORTED | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("ff0e733c-2a5a-4ad1-97c4-646b65c46120")
     static final long ERRCODE_IO_GENERAL = (13 | ErrorTranslator.ERRCODE_CLASS_GENERAL | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("9ef02b52-1325-489a-834a-b232a69eec92")
     static final long ERRCODE_IO_TOOMANYOPENFILES = (14 | ErrorTranslator.ERRCODE_CLASS_SPACE | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("ac02ffec-a06e-4dd8-9e95-34f1781f83db")
     static final long ERRCODE_IO_CANTREAD = (15 | ErrorTranslator.ERRCODE_CLASS_READ | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("b9f77ce7-b330-4fe0-9560-9cbba3b506e5")
     static final long ERRCODE_IO_CANTWRITE = (16 | ErrorTranslator.ERRCODE_CLASS_WRITE | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("72fdd799-1fc4-4ab6-ad3b-85b64920a7ed")
     static final long ERRCODE_IO_OUTOFMEMORY = (17 | ErrorTranslator.ERRCODE_CLASS_SPACE | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("105c6e75-a96d-478e-937a-6b7aef086c29")
     static final long ERRCODE_IO_CANTSEEK = (18 | ErrorTranslator.ERRCODE_CLASS_GENERAL | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("d9b66ef7-5385-49bb-b42f-45f93cfd6bfc")
     static final long ERRCODE_IO_CANTTELL = (19 | ErrorTranslator.ERRCODE_CLASS_GENERAL | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("d8c85e5f-11c6-47f3-a5e5-1de3e5955a79")
     static final long ERRCODE_IO_WRONGVERSION = (20 | ErrorTranslator.ERRCODE_CLASS_VERSION | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("89c5f3b7-e38f-4ebb-8399-98a30c78fa49")
     static final long ERRCODE_IO_WRONGFORMAT = (21 | ErrorTranslator.ERRCODE_CLASS_FORMAT | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("9a5082f8-cb52-4ce0-b337-a449f70c5283")
     static final long ERRCODE_IO_INVALIDCHAR = (22 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("9ea32e0e-d264-4ef2-94f7-462255dc996c")
     static final long ERRCODE_IO_UNKNOWN = (23 | ErrorTranslator.ERRCODE_CLASS_UNKNOWN | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("4b75dfd0-a6e5-4a4f-9f4b-00a7bac333f7")
     static final long ERRCODE_IO_INVALIDACCESS = (24 | ErrorTranslator.ERRCODE_CLASS_ACCESS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("0341b71e-5e27-4ca9-b339-f84b60ad6137")
     static final long ERRCODE_IO_CANTCREATE = (25 | ErrorTranslator.ERRCODE_CLASS_CREATE | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("c7fccca5-9ca4-4a5e-bc24-2d67c0c4b156")
     static final long ERRCODE_IO_INVALIDPARAMETER = (26 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("caa04bc4-ea50-4c76-8cf1-efd7ecfa10e6")
     static final long ERRCODE_IO_ABORT = (27 | ErrorTranslator.ERRCODE_CLASS_ABORT | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("7f11a460-10b1-40bb-9989-34d15fd437e9")
     static final long ERRCODE_IO_NOTEXISTSPATH = (28 | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("fd844125-d81e-4b9a-ae00-69771fa8b5fe")
     static final long ERRCODE_IO_PENDING = (29 | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("03c245fe-9752-427d-8425-d0c482f89154")
     static final long ERRCODE_IO_RECURSIVE = (30 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("f4cd953a-d983-400a-a438-fa9e7e9d8998")
     static final long ERRCODE_IO_NAMETOOLONG = (31 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("c38ddab9-2ad4-41d1-8dee-701eb6d24b0e")
     static final long ERRCODE_IO_INVALIDLENGTH = (32 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("dd58d962-570a-421d-a4a0-155a3cf50436")
     static final long ERRCODE_IO_CURRENTDIR = (33 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("1cd364ef-b21f-426c-a5f9-d7775752a7e6")
     static final long ERRCODE_IO_NOTSAMEDEVICE = (34 | ErrorTranslator.ERRCODE_CLASS_PARAMETER | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("63ac7d98-942e-406b-8360-ac3f6d12a8d2")
     static final long ERRCODE_IO_DEVICENOTREADY = (35 | ErrorTranslator.ERRCODE_CLASS_READ | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("b6e5d73f-a306-4c09-9b17-82c770a0327f")
     static final long ERRCODE_IO_BADCRC = (36 | ErrorTranslator.ERRCODE_CLASS_READ | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("27ffb8f9-516f-4d85-9b0c-b1fae8332d90")
     static final long ERRCODE_IO_WRITEPROTECTED = (37 | ErrorTranslator.ERRCODE_CLASS_ACCESS | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("debfd736-7936-46bf-9c93-99e359ec7162")
     static final long ERRCODE_IO_BROKENPACKAGE = (38 | ErrorTranslator.ERRCODE_CLASS_FORMAT | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("0f224ca3-74e9-44a0-9aeb-e83c5bd4a000")
     static final long ERRCODE_IO_NOTSTORABLEINBINARYFORMAT = (39 | ErrorTranslator.ERRCODE_CLASS_FORMAT | ErrorTranslator.ERRCODE_AREA_IO);

    @objid ("b0bfbe0a-3963-43d6-8066-55d65550ef3d")
     static final long ERRCODE_NONE = (0);

    @objid ("73c5762f-751a-4d94-ba87-3cc3a9293243")
     static final long ERRCODE_ABORT = ErrorTranslator.ERRCODE_IO_ABORT;

    /**
     * FsysErrorCodes
     */
    @objid ("05c337f5-08a5-42ce-9670-3deeecd97473")
     static final long FSYS_ERR_OK = ErrorTranslator.ERRCODE_NONE;

    @objid ("bb42a550-71b9-4812-931d-05cb9d42a9f7")
     static final long FSYS_ERR_MISPLACEDCHAR = ErrorTranslator.ERRCODE_IO_MISPLACEDCHAR;

    @objid ("2f16d70a-e297-4d1e-8570-c90d04d8b8a0")
     static final long FSYS_ERR_INVALIDCHAR = ErrorTranslator.ERRCODE_IO_INVALIDCHAR;

    @objid ("a2af62a1-c3ec-44e6-9438-b6fd63036a44")
     static final long FSYS_ERR_NOTEXISTS = ErrorTranslator.ERRCODE_IO_NOTEXISTS;

    @objid ("80c64a80-f725-4b56-aab4-1678d8150d81")
     static final long FSYS_ERR_ALREADYEXISTS = ErrorTranslator.ERRCODE_IO_ALREADYEXISTS;

    @objid ("043b8d28-b3dc-4e0c-a4eb-242d228c56a7")
     static final long FSYS_ERR_NOTADIRECTORY = ErrorTranslator.ERRCODE_IO_NOTADIRECTORY;

    @objid ("d01bf70e-5aeb-48d9-b5ea-767298d36349")
     static final long FSYS_ERR_NOTAFILE = ErrorTranslator.ERRCODE_IO_NOTAFILE;

    @objid ("0bc20f1c-6c54-44c8-aef6-224eeb469024")
     static final long FSYS_ERR_INVALIDDEVICE = ErrorTranslator.ERRCODE_IO_INVALIDDEVICE;

    @objid ("ee4cb300-670e-4952-845a-ffe2d96df05f")
     static final long FSYS_ERR_ACCESSDENIED = ErrorTranslator.ERRCODE_IO_ACCESSDENIED;

    @objid ("cbb51a7e-6988-4260-8386-6f13b9a58f74")
     static final long FSYS_ERR_LOCKVIOLATION = ErrorTranslator.ERRCODE_IO_LOCKVIOLATION;

    @objid ("15e7ea70-888e-44e5-8ba6-a5bd0916ccc0")
     static final long FSYS_ERR_VOLUMEFULL = ErrorTranslator.ERRCODE_IO_OUTOFSPACE;

    @objid ("cd108a0c-d741-468a-b620-ffd15ae5d89c")
     static final long FSYS_ERR_ISWILDCARD = ErrorTranslator.ERRCODE_IO_ISWILDCARD;

    @objid ("c6f0419b-609a-4f7d-b531-d1572b8d7c77")
     static final long FSYS_ERR_NOTSUPPORTED = ErrorTranslator.ERRCODE_IO_NOTSUPPORTED;

    @objid ("e5e0d5c7-d4e9-4b29-98e8-4f5ce1bee8d7")
     static final long FSYS_ERR_UNKNOWN = ErrorTranslator.ERRCODE_IO_UNKNOWN;

    /**
     * StreamErrorCodes
     */
    @objid ("f1eba178-0ea3-4728-8b5f-70924cb4e836")
     static final long SVSTREAM_OK = ErrorTranslator.ERRCODE_NONE;

    @objid ("870b161e-5eaa-42cc-9110-7dae0d9a4152")
     static final long SVSTREAM_GENERALERROR = ErrorTranslator.ERRCODE_IO_GENERAL;

    @objid ("d9f2ed07-fb9c-4694-9961-93ca52d93504")
     static final long SVSTREAM_FILE_NOT_FOUND = ErrorTranslator.ERRCODE_IO_NOTEXISTS;

    @objid ("ae1ecec9-530d-475b-ac24-e1d3b072f020")
     static final long SVSTREAM_PATH_NOT_FOUND = ErrorTranslator.ERRCODE_IO_NOTEXISTSPATH;

    @objid ("05a1ccc0-6a7f-4c30-9439-391e3c43c3e8")
     static final long SVSTREAM_TOO_MANY_OPEN_FILES = ErrorTranslator.ERRCODE_IO_TOOMANYOPENFILES;

    @objid ("3fd1857b-1d3c-4e2b-8b76-f683c9f4c2c6")
     static final long SVSTREAM_ACCESS_DENIED = ErrorTranslator.ERRCODE_IO_ACCESSDENIED;

    @objid ("b24dfd58-fec7-4d10-ac11-a61f54e8e455")
     static final long SVSTREAM_SHARING_VIOLATION = ErrorTranslator.ERRCODE_IO_LOCKVIOLATION;

    @objid ("9ec795ce-cdd9-4099-b936-c1205262d78a")
     static final long SVSTREAM_LOCKING_VIOLATION = ErrorTranslator.ERRCODE_IO_LOCKVIOLATION;

    @objid ("011083fb-c73c-4e4d-ac4f-a9b6ee0a80b7")
     static final long SVSTREAM_SHARE_BUFF_EXCEEDED = ErrorTranslator.ERRCODE_IO_LOCKVIOLATION;

    @objid ("f733189b-2edf-4a9e-b438-b4986d081316")
     static final long SVSTREAM_INVALID_ACCESS = ErrorTranslator.ERRCODE_IO_INVALIDACCESS;

    @objid ("aa2b69f8-ac68-4d80-9f0a-0365a3b63eb2")
     static final long SVSTREAM_INVALID_HANDLE = ErrorTranslator.ERRCODE_IO_GENERAL;

    @objid ("5a03a2a4-df56-42a2-a2a8-4b28814156fa")
     static final long SVSTREAM_CANNOT_MAKE = ErrorTranslator.ERRCODE_IO_CANTCREATE;

    @objid ("064fc19f-43f6-473a-a926-434785fcf1d9")
     static final long SVSTREAM_INVALID_PARAMETER = ErrorTranslator.ERRCODE_IO_INVALIDPARAMETER;

    @objid ("f67411b8-fc00-4df4-b842-fb8d4437041c")
     static final long SVSTREAM_READ_ERROR = ErrorTranslator.ERRCODE_IO_CANTREAD;

    @objid ("4bff9071-348c-400b-9767-3616f25df7fb")
     static final long SVSTREAM_WRITE_ERROR = ErrorTranslator.ERRCODE_IO_CANTWRITE;

    @objid ("256e7d24-cdc4-49be-8a22-d295c2ed5a99")
     static final long SVSTREAM_SEEK_ERROR = ErrorTranslator.ERRCODE_IO_CANTSEEK;

    @objid ("be11afa2-12e1-4edb-8856-e22f35aecdbd")
     static final long SVSTREAM_TELL_ERROR = ErrorTranslator.ERRCODE_IO_CANTTELL;

    @objid ("e8912f68-dc41-4baa-ab3c-054bec88dc4d")
     static final long SVSTREAM_OUTOFMEMORY = ErrorTranslator.ERRCODE_IO_OUTOFMEMORY;

    @objid ("a097149e-6f7c-4e6e-8bfb-293cb713da5e")
     static final long SVSTREAM_FILEFORMAT_ERROR = ErrorTranslator.ERRCODE_IO_WRONGFORMAT;

    @objid ("166adc2b-f67d-47c3-9625-31b96fbfe717")
     static final long SVSTREAM_WRONGVERSION = ErrorTranslator.ERRCODE_IO_WRONGVERSION;

    @objid ("6767ee7f-c09c-473e-b0af-f312c989b9f8")
     static final long SVSTREAM_DISK_FULL = ErrorTranslator.ERRCODE_IO_OUTOFSPACE;

    /**
     * Fuer die EditEngine:
     */
    @objid ("f57973a8-2836-4566-afa5-b1de834a0ca6")
     static final long SVSTREAM_ERRBASE_USER = ErrorTranslator.ERRCODE_AREA_LIB1;

    @objid ("0078d964-97c0-4488-9058-b918b335954d")
     static final long PRINTER_OK = ErrorTranslator.ERRCODE_NONE;

    @objid ("670f6faa-e1a7-4875-84de-421017e46284")
     static final long PRINTER_ABORT = ErrorTranslator.ERRCODE_IO_ABORT;

    @objid ("131c394e-ebfa-4a3a-b2c3-99fa143394b5")
     static final long PRINTER_OUTOFMEMORY = ErrorTranslator.ERRCODE_IO_OUTOFMEMORY;

    @objid ("2df0514c-b32a-4322-890b-e79f2d008de0")
     static final long PRINTER_GENERALERROR = ErrorTranslator.ERRCODE_IO_GENERAL;

    @objid ("bffbb753-2886-4998-a260-8fbe55f621b0")
     static final long PRINTER_ACCESSDENIED = ErrorTranslator.ERRCODE_IO_ACCESSDENIED;

    @objid ("d7f4a611-0abd-4bcd-94fd-d4e4580d8a5c")
     static final long ERRCODE_INET_NAME_RESOLVE = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_READ | 1);

    @objid ("83491e2f-4ff6-477b-bc9e-249ea7abfece")
     static final long ERRCODE_INET_CONNECT = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_READ | 2);

    @objid ("405648a4-dda1-49a2-b08f-7b8760fc9ba4")
     static final long ERRCODE_INET_READ = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_READ | 3);

    @objid ("497f3424-30e7-493e-bdca-94d421fd99bd")
     static final long ERRCODE_INET_WRITE = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_WRITE | 4);

    @objid ("ded0f248-4763-4554-a092-22f4eace4a0e")
     static final long ERRCODE_INET_GENERAL = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_WRITE | 5);

    @objid ("845938ec-38ae-4873-bbdd-84b2cf83ead4")
     static final long ERRCODE_INET_OFFLINE = (ErrorTranslator.ERRCODE_AREA_INET | ErrorTranslator.ERRCODE_CLASS_READ | 6);

    @objid ("dfcbc2a6-3657-4f52-a6a7-b231d361a2df")
    private static Map<Long, String> errCodeTable;

// ------------- ERRCODE_SFX_ : http://cgit.freedesktop.org/libreoffice/core/tree/svtools/inc/svtools/sfxecode.hxx
    @objid ("1a1a4274-5b78-445a-a46b-8e8834e4606f")
     static final long ERRCODE_SFX_NOSTDTEMPLATE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_PATH | 1);

    @objid ("dffdde7e-958f-4827-bad1-2b6a8ea12343")
     static final long ERRCODE_SFX_NOTATEMPLATE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_FORMAT | 2);

    @objid ("235d48f7-a6cc-46c1-9cb6-98ac6340a604")
     static final long ERRCODE_SFX_GENERAL = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_GENERAL | 3);

    @objid ("77b8ad51-2305-4367-893d-7b964832cd95")
     static final long ERRCODE_SFX_DOLOADFAILED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 4);

    @objid ("79421ce9-c9d7-4e79-9520-396dd6472d1f")
     static final long ERRCODE_SFX_DOSAVECOMPLETEDFAILED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_WRITE | 5);

    @objid ("7251e38a-47fb-425e-bdec-444171c15427")
     static final long ERRCODE_SFX_COMMITFAILED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_WRITE | 6);

    @objid ("82e772d5-33cc-4ba9-9fda-c6508f02e6b8")
     static final long ERRCODE_SFX_HANDSOFFFAILED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_GENERAL | 7);

    @objid ("7150cf1d-e037-43c6-b08d-d02716228011")
     static final long ERRCODE_SFX_DOINITNEWFAILED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 8);

    @objid ("4610beed-35db-4e09-9d2f-d9564163d520")
     static final long ERRCODE_SFX_CANTREADDOCINFO = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_FORMAT | 9);

    @objid ("ee4f3edc-452e-4eba-a84f-29a8e07adb96")
     static final long ERRCODE_SFX_ALREADYOPEN = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_ALREADYEXISTS | 10);

    @objid ("3aa75bd9-c1a5-4625-bae1-886743065bb1")
     static final long ERRCODE_SFX_WRONGPASSWORD = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 11);

    @objid ("18c74018-98b5-479b-8fc2-9593b6c848f2")
     static final long ERRCODE_SFX_DOCUMENTREADONLY = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_WRITE | 12);

    @objid ("e61ffa52-4f77-41b3-bd3e-473e59b6a6cd")
     static final long ERRCODE_SFX_OLEGENERAL = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 14);

    @objid ("2b6c7fc9-4208-4b3e-93fc-1c8223a161ad")
     static final long ERRCODE_SFXMSG_STYLEREPLACE = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 13);

    @objid ("7a4afe23-faf2-42a2-9d7a-57f11cf39c58")
     static final long ERRCODE_SFX_TEMPLATENOTFOUND = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 15);

    @objid ("1aaf4c42-821f-4dd3-91c9-6e98d607d60d")
     static final long ERRCODE_SFX_ISRELATIVE = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 16);

    @objid ("a6c177a2-dab8-4757-8e2f-b8c5679c1c9b")
     static final long ERRCODE_SFX_FORCEDOCLOAD = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 17);

    @objid ("f757cc61-860a-4312-8880-38fcd8c8e833")
     static final long ERRCODE_SFX_CANTFINDORIGINAL = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_GENERAL | 19);

    @objid ("4e64d775-f177-4c19-8b70-9f44157e9bb1")
     static final long ERRCODE_SFX_RESTART = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_GENERAL | 20);

    @objid ("8ad5ff5d-f3e4-4af2-a2de-0596150c450a")
     static final long ERRCODE_SFX_CANTCREATECONTENT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 21);

    @objid ("c9051899-5826-411d-85de-a15fc8bc5145")
     static final long ERRCODE_SFX_CANTCREATELINK = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 22);

    @objid ("879dfd34-cfe5-4ded-abec-4f49daef03b8")
     static final long ERRCODE_SFX_WRONGBMKFORMAT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_FORMAT | 23);

    @objid ("54bc11ce-a204-4bb9-a97c-d346c80b6e70")
     static final long ERRCODE_SFX_WRONGICONFILE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_FORMAT | 24);

    @objid ("c9b813aa-288b-45ce-ae7b-6f4f07411a27")
     static final long ERRCODE_SFX_CANTDELICONFILE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_ACCESS | 25);

    @objid ("c61f56ae-c092-4ac5-9bbd-e306b65a454c")
     static final long ERRCODE_SFX_CANTWRITEICONFILE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_ACCESS | 26);

    @objid ("b580f3f5-8c71-47e8-aa11-5faa647a4edc")
     static final long ERRCODE_SFX_CANTRENAMECONTENT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_ACCESS | 27);

    @objid ("a6f85f80-c128-433a-8705-a32d344090b1")
     static final long ERRCODE_SFX_INVALIDBMKPATH = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_PATH | 28);

    @objid ("2e3c68ae-e4a2-4d55-8f5c-1c4e0fd2b67b")
     static final long ERRCODE_SFX_CANTWRITEURLCFGFILE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_ACCESS | 29);

    @objid ("f44cdf1e-2933-46f9-9bee-7f3809ba2840")
     static final long ERRCODE_SFX_WRONGURLCFGFORMAT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_FORMAT | 30);

    @objid ("d5d2bab1-f5df-4350-935d-133348af8ece")
     static final long ERRCODE_SFX_NODOCUMENT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 31);

    @objid ("96698bf6-cb7b-4b48-9629-2a7b64fbb64b")
     static final long ERRCODE_SFX_INVALIDLINK = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 32);

    @objid ("bf43febc-ca75-4a1a-bd91-98e4f222d225")
     static final long ERRCODE_SFX_INVALIDTRASHPATH = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_PATH | 33);

    @objid ("f72c9aba-ae41-4fcf-9706-c491d1e40b5a")
     static final long ERRCODE_SFX_NOTRESTORABLE = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 34);

    @objid ("0e060998-fef2-4c8b-9548-bd9f69f45bcc")
     static final long ERRCODE_SFX_NOTRASH = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 35);

    @objid ("f79ee472-96ba-4d42-a4c1-e0caa739a31e")
     static final long ERRCODE_SFX_INVALIDSYNTAX = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_PATH | 36);

    @objid ("e9ae4b0f-e742-4965-9c0a-4b458094a6c8")
     static final long ERRCODE_SFX_CANTCREATEFOLDER = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 37);

    @objid ("dcc7ca87-cd05-4246-b345-76c58a16315d")
     static final long ERRCODE_SFX_CANTRENAMEFOLDER = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_PATH | 38);

    @objid ("3c396c76-d4fb-455f-9f04-a5b0fb8ba5a2")
     static final long ERRCODE_SFX_WRONG_CDF_FORMAT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 39);

    @objid ("b6f7e428-1b9c-400b-9173-0eed0cab502f")
     static final long ERRCODE_SFX_EMPTY_SERVER = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 40);

    @objid ("37dc0c44-97c6-4ab7-871a-3a0121f5697e")
     static final long ERRCODE_SFX_NO_ABOBOX = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 41);

    @objid ("54980bb7-8d24-4895-ae11-d81e09ad6fa9")
     static final long ERRCODE_SFX_CANTGETPASSWD = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 42);

    @objid ("ca30e697-d85b-448c-a030-f451062bce64")
     static final long ERRCODE_SFX_TARGETFILECORRUPTED = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_READ | 43);

    @objid ("d4fc6c92-0865-4a70-bcbe-bc0386e8c082")
     static final long ERRCODE_SFX_NOMOREDOCUMENTSALLOWED = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 44);

    @objid ("542f4506-ef3a-4bc0-b487-f94e34bc558b")
     static final long ERRCODE_SFX_NOFILTER = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NOTEXISTS | 45);

    @objid ("af062512-7b09-4716-825b-4f7904d67fee")
     static final long ERRCODE_SFX_FORCEQUIET = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 47);

    @objid ("7283dca4-6acd-4cc5-af6c-ac29aad57017")
     static final long ERRCODE_SFX_CONSULTUSER = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 48);

    @objid ("8f8967bc-2c94-4e28-88d1-fdcc92db2963")
     static final long ERRCODE_SFX_NEVERCHECKCONTENT = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 49);

    @objid ("42c5c9bf-0f29-4647-be64-6e39ab44de74")
     static final long ERRCODE_SFX_CANTCREATEBACKUP = (ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_CREATE | 50);

    @objid ("b043fe89-252f-4534-95db-4e41b1d7c862")
     static final long ERRCODE_SFX_MACROS_SUPPORT_DISABLED = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 51);

    @objid ("2174e974-7be5-4bdc-9421-df7216570003")
     static final long ERRCODE_SFX_DOCUMENT_MACRO_DISABLED = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 52);

    @objid ("a5b63821-607e-4f95-a2ce-e1cd5b163270")
     static final long ERRCODE_SFX_BROKENSIGNATURE = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 53);

    @objid ("07c43c9c-48e1-4c04-bccd-3331b672a344")
     static final long ERRCODE_SFX_SHARED_NOPASSWORDCHANGE = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 54);

    @objid ("ce1d9d88-d3eb-4521-afc1-5457e19475b5")
     static final long ERRCODE_SFX_INCOMPLETE_ENCRYPTION = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 55);

    @objid ("abf02c73-ed66-4e16-b602-92b628b66c78")
     static final long ERRCODE_SFX_DOCUMENT_MACRO_DISABLED_MAC = (ErrorTranslator.ERRCODE_WARNING_MASK | ErrorTranslator.ERRCODE_AREA_SFX | ErrorTranslator.ERRCODE_CLASS_NONE | 56);

// -----------
    @objid ("81479bae-d796-48ef-8050-858b97ffa4d7")
     static final long SO_ERR = (ErrorTranslator.ERRCODE_AREA_SO | ErrorTranslator.ERRCODE_CLASS_SO);

    @objid ("b07ae017-f178-44a1-973a-9a7b36855068")
     static final long SO_WRN = (ErrorTranslator.ERRCODE_AREA_SO | ErrorTranslator.ERRCODE_CLASS_SO | ErrorTranslator.ERRCODE_WARNING_MASK);

    @objid ("05851478-ea83-49b7-add5-3764412e28dc")
     static final long ERRCODE_SO_GENERALERROR = (ErrorTranslator.SO_ERR | 1);

    @objid ("67117072-7736-470f-9511-0004ba183220")
     static final long ERRCODE_SO_CANT_BINDTOSOURCE = (ErrorTranslator.SO_ERR | 2);

    @objid ("2663eb6a-9bbb-4c47-8277-b3c94a80734d")
     static final long ERRCODE_SO_NOCACHE_UPDATED = (ErrorTranslator.SO_ERR | 3);

    @objid ("d0f80d8c-026c-4892-8870-41de12770cfc")
     static final long ERRCODE_SO_SOMECACHES_NOTUPDATED = (ErrorTranslator.SO_WRN | 4);

    @objid ("b75cbb31-6b27-4a4a-8805-7648034419ab")
     static final long ERRCODE_SO_MK_UNAVAILABLE = (ErrorTranslator.SO_ERR | 5);

    @objid ("ea05ce68-6c6e-499e-8f1a-39f8ae2cf841")
     static final long ERRCODE_SO_E_CLASSDIFF = (ErrorTranslator.SO_ERR | 6);

    @objid ("40564652-62e2-4274-85e2-c25e8dd0a1cb")
     static final long ERRCODE_SO_MK_NO_OBJECT = (ErrorTranslator.SO_ERR | 7);

    @objid ("fdc518c5-89a5-4f3e-8496-61d055d0a4a5")
     static final long ERRCODE_SO_MK_EXCEEDED_DEADLINE = (ErrorTranslator.SO_ERR | 8);

    @objid ("74f9c9e8-5e16-4c1c-8670-e334532e8e89")
     static final long ERRCODE_SO_MK_CONNECT_MANUALLY = (ErrorTranslator.SO_ERR | 9);

    @objid ("0567aa9a-684f-444d-a65a-cc7c129bef93")
     static final long ERRCODE_SO_MK_INTERMEDIATE_INTERFACE_NOT_SUPPORTED = (ErrorTranslator.SO_ERR | 10);

    @objid ("01995c70-ec61-4796-adbf-a42ac5a36c5b")
     static final long ERRCODE_SO_NO_INTERFACE = (ErrorTranslator.SO_ERR | 11);

    @objid ("ce697841-3fb3-4a90-9620-8d1a17aef5c3")
     static final long ERRCODE_SO_OUT_OF_MEMORY = (ErrorTranslator.SO_ERR | 12);

    @objid ("16bdc99a-c5f3-4304-a477-a97955f971e5")
     static final long ERRCODE_SO_MK_SYNTAX = (ErrorTranslator.SO_ERR | 13);

    @objid ("f8b56543-57f1-4e1a-b4ba-759fbf9b6ad8")
     static final long ERRCODE_SO_MK_REDUCED_TO_SELF = (ErrorTranslator.SO_WRN | 14);

    @objid ("be975e07-0ac4-4f7d-a6fe-ebc66a261c54")
     static final long ERRCODE_SO_MK_NO_INVERSE = (ErrorTranslator.SO_ERR | 15);

    @objid ("13081439-310c-4223-86ac-75ed564b4adf")
     static final long ERRCODE_SO_MK_NO_PREFIX = (ErrorTranslator.SO_ERR | 16);

    @objid ("2e46c41d-af8f-4a4b-ad51-e1643fa3f172")
     static final long ERRCODE_SO_MK_HIM = (ErrorTranslator.SO_WRN | 17);

    @objid ("a44c8df7-1277-4b6e-bd18-31e5263e2149")
     static final long ERRCODE_SO_MK_US = (ErrorTranslator.SO_WRN | 18);

    @objid ("cb094672-af2c-4d11-a4b1-30759802caa1")
     static final long ERRCODE_SO_MK_ME = (ErrorTranslator.SO_WRN | 19);

    @objid ("aac4dcc4-1bf6-48ee-966f-aee68ff0bea7")
     static final long ERRCODE_SO_MK_NOT_BINDABLE = (ErrorTranslator.SO_ERR | 20);

    @objid ("f4c21175-206b-4c0d-966a-e9bbff99f28a")
     static final long ERRCODE_SO_NOT_IMPLEMENTED = (ErrorTranslator.SO_ERR | 21);

    @objid ("d6a29e72-7ff4-45ea-9abb-2e11d2ed3477")
     static final long ERRCODE_SO_MK_NO_STORAGE = (ErrorTranslator.SO_ERR | 22);

    @objid ("6992f958-a8c9-4faa-9d5c-e522328f1ecd")
     static final long ERRCODE_SO_FALSE = (ErrorTranslator.SO_WRN | 23);

    @objid ("ca096fb7-70bd-46a1-8c05-e2476ecbe80d")
     static final long ERRCODE_SO_MK_NEED_GENERIC = (ErrorTranslator.SO_ERR | 24);

    @objid ("426d129e-b9f1-4805-88f7-b17a86c4aebc")
     static final long ERRCODE_SO_PENDING = (ErrorTranslator.SO_ERR | 25);

    @objid ("82e02add-441b-4128-94f9-d9d6d13bd828")
     static final long ERRCODE_SO_NOT_INPLACEACTIVE = (ErrorTranslator.SO_ERR | 26);

    @objid ("869085e8-0b72-4cdc-88ce-c4716f468aff")
     static final long ERRCODE_SO_LINDEX = (ErrorTranslator.SO_ERR | 27);

    @objid ("9bc25620-0579-43fd-910a-076db4463c82")
     static final long ERRCODE_SO_CANNOT_DOVERB_NOW = (ErrorTranslator.SO_WRN | 28);

    @objid ("c1846449-8cf0-41b1-8d7d-d7fa2b2497b3")
     static final long ERRCODE_SO_OLEOBJ_INVALIDHWND = (ErrorTranslator.SO_WRN | 29);

    @objid ("9f1fa7c8-703b-4646-8768-a4f2e568294d")
     static final long ERRCODE_SO_NOVERBS = (ErrorTranslator.SO_ERR | 30);

    @objid ("d7911b86-bccf-47f0-b570-860bdbbf015d")
     static final long ERRCODE_SO_INVALIDVERB = (ErrorTranslator.SO_WRN | 31);

    @objid ("46514d56-5a2a-4a4a-b8df-f0834b19ba39")
     static final long ERRCODE_SO_MK_CONNECT = (ErrorTranslator.SO_ERR | 32);

    @objid ("7c83b9c5-cfbd-4476-b2d6-93f55f099b90")
     static final long ERRCODE_SO_NOTIMPL = (ErrorTranslator.SO_ERR | 33);

    @objid ("cfde0e7b-9244-40a6-92ef-64b02f293266")
     static final long ERRCODE_SO_MK_CANTOPENFILE = (ErrorTranslator.SO_ERR | 34);

    /**
     * Initialize the error code table.
     */
    @objid ("f5a5ece6-1f27-4fa9-bd3a-14ef4c62c312")
    private static void initErrorCodeTable() {
        ErrorTranslator.errCodeTable = new HashMap<>();
        
        final ResourceBundle msgFile = ResourceBundle.getBundle("richnotes_libreoffice");
        final String errTemplate = msgFile.getString("ErrorCodeIOException.msg");
        
        for (Field f : ErrorTranslator.class.getDeclaredFields()) {
            Long key = 0L;
            String name = f.getName();
        
            if (f.getType() == long.class && name.startsWith("ERRCODE_")) {
                try {
                    key = (Long) f.get(null);
                    String msg = msgFile.getString(name);
                    if (msg == null) {
                        msg = name;
                    }
        
                    ErrorTranslator.errCodeTable.put(key, MessageFormat.format(errTemplate, key, msg));
                } catch (IllegalArgumentException e) {
                    LibreOfficeEditors.LOG.warning(e);
                } catch (IllegalAccessException e) {
                    LibreOfficeEditors.LOG.warning(e);
                } catch (MissingResourceException e) {
                    // LibreOfficeEditors.LOG.info(e);
                    ErrorTranslator.errCodeTable.put(key, MessageFormat.format(errTemplate, key, name));
                }
            }
        }
    }

    /**
     * Get the error code label for the given ErrorCodeIOException.
     * @param exc The error
     * @return The error label.
     */
    @objid ("ff821f51-28f8-46c4-b168-40bf279c6df8")
    public static String getErrorMessage(final ErrorCodeIOException exc) {
        if (ErrorTranslator.errCodeTable == null) {
            initErrorCodeTable();
        }
        
        String ret = ErrorTranslator.errCodeTable.get(Long.valueOf(exc.ErrCode));
        if (ret != null) {
            return ret;
        } else {
            ResourceBundle msgFile = ResourceBundle.getBundle("edition_extern_libreoffice");
            String errnomsgTemplate = msgFile.getString("ErrorCodeIOException.nomsg");
            return MessageFormat.format(errnomsgTemplate, exc.ErrCode);
            // return String.valueOf(exc.ErrCode);
        }
    }

}
