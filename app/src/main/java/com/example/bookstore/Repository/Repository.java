package com.example.bookstore.Repository;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static String[] applicationsUrls=new String[]{
            "https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/BitTorrent.exe?alt=media&token=8014e873-1b2a-41ce-b7bc-7ef14277b743" //Bitorrent Setup
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/ChromeSetup.exe?alt=media&token=26472782-4829-47ae-8065-83b902c0875b"//Chrome Setup
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/Firefox%20Installer.exe?alt=media&token=36e55960-b098-4876-9ddc-058446dd0c65" // FireFox
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/GLP_Installer_900206280_PUBG_en_detailpage.exe?alt=media&token=d1fe16dc-d459-4826-bcfe-ebe803f1391a" // PUBG Installer
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/Gameloop_Download_SpecialPackage.exe?alt=media&token=71f03fb9-0a65-40b9-925e-a7202841778b" //Gameloop
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/LDPlayer4_ens_3020_ld.exe?alt=media&token=c7cc10ad-ca32-4db9-94f4-287889fef8e9" //LD player
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/NordVPN_4.10.3_10k_Premium_Accounts.zip?alt=media&token=9b439195-4b1a-414e-8a64-bdefd6f506fd" //Nord VPN
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/OperaGXSetup.exe?alt=media&token=4964a6f4-82b1-4c61-9878-11d900763f26" //OperaGX
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/OperaSetup.exe?alt=media&token=df5d45aa-940b-4482-887e-77c8bbeb26d2" //Opera Setup
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/TeamViewer_Setup.exe?alt=media&token=1c4ca4c2-5285-414f-b226-93ac8b45909d" //TeamViewer
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/UserBenchMark.exe?alt=media&token=1f165bfd-e11e-44cf-8aa0-7c0434aa3b53"//UserBench Mark
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/VSCodeUserSetup-x64-1.49.2.exe?alt=media&token=f8fa1259-8c11-4882-8e4b-1387100bbfd1" //VS Code
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/Vidmate_v4.3004.apk?alt=media&token=bee52b19-3caf-4594-8841-2e81f60433d2" //Vidmate apk
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/YoutubePlaylistDownloader-setup.exe?alt=media&token=0ee61600-8c8c-4afd-a8b7-212243485384" //Youtube player
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/cpu-z_1.79-en.exe?alt=media&token=7f953688-25e6-4bd2-90a1-8bb71303a148" //CUPZ
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/idman638build5.exe?alt=media&token=c4559790-e092-4955-a35a-d40ff24cf63e" // IDM
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/kmspico.zip?alt=media&token=e728c815-fd52-4808-8763-e936e392aaf8"//KMSPICO
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/radeon-software-adrenalin-2020-20.9.1-minimalsetup-200916_web.exe?alt=media&token=884e401c-1aad-42c8-b9c4-30f48f1f4a5f" // radeon software
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/torbrowser-install-win64-10.0_en-US.exe?alt=media&token=1b5efd2b-a508-4817-acdc-30ea121b3db9" // TorBrowser
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/uTorrent.exe?alt=media&token=fbc1b995-8614-4ba7-8db0-2679537ec567" // Torrent
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/vlc-3.0.11-win32.exe?alt=media&token=51a90d2d-4e9f-4971-aa4a-ebd4f646e037" //VLC
            ,"https://firebasestorage.googleapis.com/v0/b/fileuploadtest-98f99.appspot.com/o/winrar-x64-590.exe?alt=media&token=d735deea-bf6b-431e-bf3c-ab1eb07095ce" //Winrar
    };

    public static String[] getItemsUrlArray(){
        return applicationsUrls;
    }
}
