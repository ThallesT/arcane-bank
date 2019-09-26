package com.gtbr.arcanebank.servico;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.text.Element;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


@Component
public class MailService {
    private final String HTML_DATA = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\"\n" +
            "        \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"\n" +
            "      xmlns:v=\"urn:schemas-microsoft-com:vml\">\n" +
            "<head>\n" +
            "    <!--[if gte mso 9]>\n" +
            "    <xml>\n" +
            "        <o:OfficeDocumentSettings>\n" +
            "            <o:AllowPNG/>\n" +
            "            <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
            "        </o:OfficeDocumentSettings>\n" +
            "    </xml><![endif]-->\n" +
            "    <meta charset=\"UTF-8\"/>\n" +
            "    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>\n" +
            "    <meta content=\"width=device-width\" name=\"viewport\"/>\n" +
            "    <!--[if !mso]><!-->\n" +
            "    <meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"/>\n" +
            "    <!--<![endif]-->\n" +
            "    <title></title>\n" +
            "    <!--[if !mso]><!-->\n" +
            "    <link href=\"https://fonts.googleapis.com/css?family=Ubuntu\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
            "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat\" rel=\"stylesheet\" type=\"text/css\"/>\n" +
            "    <!--<![endif]-->\n" +
            "    <style type=\"text/css\">\n" +
            "        body {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "\n" +
            "        table,\n" +
            "        td,\n" +
            "        tr {\n" +
            "            vertical-align: top;\n" +
            "            border-collapse: collapse;\n" +
            "        }\n" +
            "\n" +
            "        * {\n" +
            "            line-height: inherit;\n" +
            "        }\n" +
            "\n" +
            "        a[x-apple-data-detectors=true] {\n" +
            "            color: inherit !important;\n" +
            "            text-decoration: none !important;\n" +
            "        }\n" +
            "    </style>\n" +
            "    <style id=\"media-query\" type=\"text/css\">\n" +
            "        @media (max-width: 720px) {\n" +
            "\n" +
            "            .block-grid,\n" +
            "            .col {\n" +
            "                min-width: 320px !important;\n" +
            "                max-width: 100% !important;\n" +
            "                display: block !important;\n" +
            "            }\n" +
            "\n" +
            "            .block-grid {\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            .col {\n" +
            "                width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            .col > div {\n" +
            "                margin: 0 auto;\n" +
            "            }\n" +
            "\n" +
            "            img.fullwidth,\n" +
            "            img.fullwidthOnMobile {\n" +
            "                max-width: 100% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col {\n" +
            "                min-width: 0 !important;\n" +
            "                display: table-cell !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack.two-up .col {\n" +
            "                width: 50% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num4 {\n" +
            "                width: 33% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num8 {\n" +
            "                width: 66% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num4 {\n" +
            "                width: 33% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num3 {\n" +
            "                width: 25% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num6 {\n" +
            "                width: 50% !important;\n" +
            "            }\n" +
            "\n" +
            "            .no-stack .col.num9 {\n" +
            "                width: 75% !important;\n" +
            "            }\n" +
            "\n" +
            "            .video-block {\n" +
            "                max-width: none !important;\n" +
            "            }\n" +
            "\n" +
            "            .mobile_hide {\n" +
            "                min-height: 0px;\n" +
            "                max-height: 0px;\n" +
            "                max-width: 0px;\n" +
            "                display: none;\n" +
            "                overflow: hidden;\n" +
            "                font-size: 0px;\n" +
            "            }\n" +
            "\n" +
            "            .desktop_hide {\n" +
            "                display: block !important;\n" +
            "                max-height: none !important;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body class=\"clean-body\" style=\"margin: 0; padding: 0; -webkit-text-size-adjust: 100%; background-color: #5F386D;\">\n" +
            "<!--[if IE]>\n" +
            "<div class=\"ie-browser\"><![endif]-->\n" +
            "<table bgcolor=\"#5F386D\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\"\n" +
            "       style=\"table-layout: fixed; vertical-align: top; min-width: 320px; Margin: 0 auto; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #5F386D; width: 100%;\"\n" +
            "       valign=\"top\" width=\"100%\">\n" +
            "    <tbody>\n" +
            "    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
            "        <td style=\"word-break: break-word; vertical-align: top;\" valign=\"top\">\n" +
            "            <!--[if (mso)|(IE)]>\n" +
            "            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                <tr>\n" +
            "                    <td align=\"center\" style=\"background-color:#5F386D\"><![endif]-->\n" +
            "            <div style=\"background-color:transparent;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:transparent;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:5px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <div align=\"center\" class=\"img-container center autowidth\">\n" +
            "                                        <!--[if mso]>\n" +
            "                                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                            <tr style=\"line-height:0px\">\n" +
            "                                                <td style=\"\" align=\"center\"><![endif]--><img align=\"center\"\n" +
            "                                                                                             alt=\"I'm an image\"\n" +
            "                                                                                             border=\"0\"\n" +
            "                                                                                             class=\"center autowidth\"\n" +
            "                                                                                             src=\"https://i.imgur.com/SpwF32F.png\"\n" +
            "                                                                                             style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 125px; display: block;\"\n" +
            "                                                                                             title=\"I'm an image\"\n" +
            "                                                                                             width=\"125\"/>\n" +
            "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
            "                                    </div>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-color:transparent;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:transparent;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:5px; padding-bottom:20px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:5px; padding-bottom:20px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <div align=\"center\" class=\"img-container center autowidth\"\n" +
            "                                         style=\"padding-right: 0px;padding-left: 0px;\">\n" +
            "                                        <!--[if mso]>\n" +
            "                                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                            <tr style=\"line-height:0px\">\n" +
            "                                                <td style=\"padding-right: 0px;padding-left: 0px;\" align=\"center\">\n" +
            "                                        <![endif]--><img align=\"center\" alt=\"Image\" border=\"0\" class=\"center autowidth\"\n" +
            "                                                         src=\"https://i.imgur.com/K7g5ocY.png\"\n" +
            "                                                         style=\"text-decoration: none; -ms-interpolation-mode: bicubic; border: 0; height: auto; width: 100%; max-width: 186px; display: block;\"\n" +
            "                                                         title=\"Image\" width=\"186\"/>\n" +
            "                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
            "                                    </div>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-image:url('https://i.imgur.com/PKSjmpF.png');background-position:top center;background-repeat:repeat;background-color:transparent;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-image:url('https://i.imgur.com/PKSjmpF.png');background-position:top center;background-repeat:repeat;background-color:transparent;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:0px; padding-bottom:0px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:0px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\"\n" +
            "                                           role=\"presentation\"\n" +
            "                                           style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
            "                                           valign=\"top\" width=\"100%\">\n" +
            "                                        <tbody>\n" +
            "                                        <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
            "                                            <td class=\"divider_inner\"\n" +
            "                                                style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 5px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px;\"\n" +
            "                                                valign=\"top\">\n" +
            "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
            "                                                       class=\"divider_content\" height=\"0\" role=\"presentation\"\n" +
            "                                                       style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 0px; width: 100%;\"\n" +
            "                                                       valign=\"top\" width=\"100%\">\n" +
            "                                                    <tbody>\n" +
            "                                                    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
            "                                                        <td height=\"0\"\n" +
            "                                                            style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
            "                                                            valign=\"top\"><span></span></td>\n" +
            "                                                    </tr>\n" +
            "                                                    </tbody>\n" +
            "                                                </table>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-color:#FFFFFF;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:#FFFFFF;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 20px; padding-left: 20px; padding-top:15px; padding-bottom:5px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:5px; padding-right: 20px; padding-left: 20px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <!--[if mso]>\n" +
            "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\">\n" +
            "                                    <![endif]-->\n" +
            "                                    <div style=\"color:#555555;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
            "                                        <div style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; line-height: 1.2; color: #555555; mso-line-height-alt: 14px;\">\n" +
            "                                            <p style=\"font-size: 28px; line-height: 1.2; text-align: center; mso-line-height-alt: 34px; margin: 0;\">\n" +
            "                                                <span style=\"font-size: 28px;\" id=\"recado\"></span></p>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                    <!--[if mso]></td></tr></table><![endif]-->\n" +
            "                                    <!--[if mso]>\n" +
            "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: Arial, sans-serif\">\n" +
            "                                    <![endif]-->\n" +
            "                                    <div style=\"color:#555555;font-family:'Helvetica Neue', Helvetica, Arial, sans-serif;line-height:1.2;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
            "                                        <div style=\"font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; font-size: 12px; line-height: 1.2; color: #555555; mso-line-height-alt: 14px;\">\n" +
            "                                            <p style=\"font-size: 20px; line-height: 1.2; text-align: center; mso-line-height-alt: 24px; margin: 0;\">\n" +
            "                                                <span style=\"font-size: 20px;\" id=\"html-token-tag\"></span></p>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                    <!--[if mso]></td></tr></table><![endif]-->\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-color:#FFFFFF;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:#FFFFFF;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:15px; padding-bottom:15px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:15px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <div align=\"center\" class=\"button-container\"\n" +
            "                                         style=\"padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
            "                                        <!--[if mso]>\n" +
            "                                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                                               style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;\">\n" +
            "                                            <tr>\n" +
            "                                                <td style=\"padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px\"\n" +
            "                                                    align=\"center\">\n" +
            "                                                    <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
            "                                                                 xmlns:w=\"urn:schemas-microsoft-com:office:word\"\n" +
            "                                                                 style=\"height:43.5pt; width:193.5pt; v-text-anchor:middle;\"\n" +
            "                                                                 arcsize=\"87%\" stroke=\"false\" fillcolor=\"#5F386D\">\n" +
            "                                                        <w:anchorlock/>\n" +
            "                                                        <v:textbox inset=\"0,0,0,0\">\n" +
            "                                                            <center style=\"color:#ffffff; font-family:'Trebuchet MS', Tahoma, sans-serif; font-size:24px\">\n" +
            "                                        <![endif]--><a id=\"botao\"\n" +
            "                                                       style=\"-webkit-text-size-adjust: none; text-decoration: none; display: inline-block; color: #ffffff; background-color: #5F386D; border-radius: 50px; -webkit-border-radius: 50px; -moz-border-radius: 50px; width: auto; width: auto; border-top: 1px solid #5F386D; border-right: 1px solid #5F386D; border-bottom: 1px solid #5F386D; border-left: 1px solid #5F386D; padding-top: 5px; padding-bottom: 5px; font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; text-align: center; mso-border-alt: none; word-break: keep-all;\"\n" +
            "                                                       target=\"_blank\"><span\n" +
            "                                            style=\"padding-left:45px;padding-right:45px;font-size:24px;display:inline-block;\">\n" +
            "<span style=\"font-size: 16px; line-height: 2; mso-line-height-alt: 32px;\"><span\n" +
            "        style=\"font-size: 24px; line-height: 48px;\"><strong>Ou clique aqui</strong></span></span>\n" +
            "</span></a>\n" +
            "                                        <!--[if mso]></center></v:textbox></v:roundrect></td></tr></table><![endif]-->\n" +
            "                                    </div>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-image:url('https://i.imgur.com/ICLTvym.png');background-position:top center;background-repeat:repeat;background-color:transparent;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-image:url('https://i.imgur.com/ICLTvym.png');background-position:top center;background-repeat:repeat;background-color:transparent;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:15px; padding-bottom:0px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:0px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"divider\"\n" +
            "                                           role=\"presentation\"\n" +
            "                                           style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
            "                                           valign=\"top\" width=\"100%\">\n" +
            "                                        <tbody>\n" +
            "                                        <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
            "                                            <td class=\"divider_inner\"\n" +
            "                                                style=\"word-break: break-word; vertical-align: top; min-width: 100%; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; padding-top: 2px; padding-right: 2px; padding-bottom: 2px; padding-left: 2px;\"\n" +
            "                                                valign=\"top\">\n" +
            "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n" +
            "                                                       class=\"divider_content\" height=\"0\" role=\"presentation\"\n" +
            "                                                       style=\"table-layout: fixed; vertical-align: top; border-spacing: 0; border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-top: 0px solid transparent; height: 0px; width: 100%;\"\n" +
            "                                                       valign=\"top\" width=\"100%\">\n" +
            "                                                    <tbody>\n" +
            "                                                    <tr style=\"vertical-align: top;\" valign=\"top\">\n" +
            "                                                        <td height=\"0\"\n" +
            "                                                            style=\"word-break: break-word; vertical-align: top; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\"\n" +
            "                                                            valign=\"top\"><span></span></td>\n" +
            "                                                    </tr>\n" +
            "                                                    </tbody>\n" +
            "                                                </table>\n" +
            "                                            </td>\n" +
            "                                        </tr>\n" +
            "                                        </tbody>\n" +
            "                                    </table>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-color:transparent;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:transparent;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:30px; padding-bottom:5px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:30px; padding-bottom:5px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <div></div>\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div style=\"background-color:#483C6C;\">\n" +
            "                <div class=\"block-grid\"\n" +
            "                     style=\"Margin: 0 auto; min-width: 320px; max-width: 700px; overflow-wrap: break-word; word-wrap: break-word; word-break: break-word; background-color: transparent;\">\n" +
            "                    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color:transparent;\">\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"\n" +
            "                               style=\"background-color:#483C6C;\">\n" +
            "                            <tr>\n" +
            "                                <td align=\"center\">\n" +
            "                                    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:700px\">\n" +
            "                                        <tr class=\"layout-full-width\" style=\"background-color:transparent\"><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]>\n" +
            "                        <td align=\"center\" width=\"700\"\n" +
            "                            style=\"background-color:transparent;width:700px; border-top: 0px solid transparent; border-left: 0px solid transparent; border-bottom: 0px solid transparent; border-right: 0px solid transparent;\"\n" +
            "                            valign=\"top\">\n" +
            "                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                <tr>\n" +
            "                                    <td style=\"padding-right: 0px; padding-left: 0px; padding-top:15px; padding-bottom:15px;\">\n" +
            "                        <![endif]-->\n" +
            "                        <div class=\"col num12\"\n" +
            "                             style=\"min-width: 320px; max-width: 700px; display: table-cell; vertical-align: top; width: 700px;\">\n" +
            "                            <div style=\"width:100% !important;\">\n" +
            "                                <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                <div style=\"border-top:0px solid transparent; border-left:0px solid transparent; border-bottom:0px solid transparent; border-right:0px solid transparent; padding-top:15px; padding-bottom:15px; padding-right: 0px; padding-left: 0px;\">\n" +
            "                                    <!--<![endif]-->\n" +
            "                                    <!--[if mso]>\n" +
            "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
            "                                        <tr>\n" +
            "                                            <td style=\"padding-right: 10px; padding-left: 10px; padding-top: 10px; padding-bottom: 10px; font-family: 'Trebuchet MS', Tahoma, sans-serif\">\n" +
            "                                    <![endif]-->\n" +
            "                                    <div style=\"color:#FFFFFF;font-family:'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif;line-height:1.5;padding-top:10px;padding-right:10px;padding-bottom:10px;padding-left:10px;\">\n" +
            "                                        <div style=\"font-family: 'Montserrat', 'Trebuchet MS', 'Lucida Grande', 'Lucida Sans Unicode', 'Lucida Sans', Tahoma, sans-serif; font-size: 12px; line-height: 1.5; color: #FFFFFF; mso-line-height-alt: 18px;\">\n" +
            "                                            <p style=\"font-size: 12px; line-height: 1.5; text-align: center; mso-line-height-alt: 18px; margin: 0;\">\n" +
            "                                                Made with <3 by Thalles</p>\n" +
            "                                            <p style=\"font-size: 12px; line-height: 1.5; text-align: center; mso-line-height-alt: 18px; margin: 0;\">\n" +
            "                                                Contact: (61)9 8299-8807</p>\n" +
            "                                        </div>\n" +
            "                                    </div>\n" +
            "                                    <!--[if mso]></td></tr></table><![endif]-->\n" +
            "                                    <!--[if (!mso)&(!IE)]><!-->\n" +
            "                                </div>\n" +
            "                                <!--<![endif]-->\n" +
            "                            </div>\n" +
            "                        </div>\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "                        <!--[if (mso)|(IE)]></td></tr></table></td></tr></table><![endif]-->\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    </tbody>\n" +
            "</table>\n" +
            "<!--[if (IE)]></div><![endif]-->\n" +
            "</body>\n" +
            "</html>";

    public void enviarEmail(String emailCliente, String codigoDeConfirmacao){
        Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("arcaneBankMail@gmail.com",
                                "arcaneLoko123");
                    }
                });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("arcaneBankMail@gmail.com"));
            //Remetente

            Address[] toUser = InternetAddress.parse(emailCliente);
            //File arquivo = new File("src\\main\\resources\\templates\\email\\tokenTemplate.html");
            Document doc = Jsoup.parse(HTML_DATA);
            doc.getElementById("recado").text("Nos do Arcane Bank agradecemos o seu interesse!");
            doc.getElementById("html-token-tag").text("Seu token: "+ codigoDeConfirmacao);
            String link = "http://gtbr.sa-east-1.elasticbeanstalk.com/cliente/confirmar-token/"+codigoDeConfirmacao;
            doc.getElementById("botao").attr("href", link);
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("E-mail automatico do ArcaneBank");//Assunto
            message.setContent(doc.outerHtml(), "text/html");



            /**Método para enviar a mensagem criada*/
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
