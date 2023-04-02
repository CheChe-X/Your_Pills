<!DOCTYPE html>
<html lang="en">
    <head>
      <!-- basic -->
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <!-- mobile metas -->
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="viewport" content="initial-scale=1, maximum-scale=1">
      <!-- site metas -->
      <title>Receitas</title>
      <meta name="keywords" content="">
      <meta name="description" content="">
      <meta name="author" content="">
      <!-- bootstrap css -->
      <link rel="stylesheet" href="css/bootstrap.min.css">
      <!-- style css -->
      <link rel="stylesheet" href="css/style.css">
      <!-- Responsive-->
      <link rel="stylesheet" href="css/responsive.css">
      <!-- fevicon -->
      <link rel="icon" href="images/fevicon.png" type="image/gif" />
      <!-- Scrollbar Custom CSS -->
      <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
      <!-- Tweaks for older IEs-->
      <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
      <!-- owl stylesheets --> 
      <link rel="stylesheet" href="css/owl.carousel.min.css">
      <link rel="stylesheet" href="css/owl.theme.default.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
    </head>
    <body>
      <!-- header section start -->
      <div class="header_section">
        <nav class="destop_header navbar navbar-expand-lg navbar-light bg-light">
          <div class="logo"></div>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a class="nav-link" href="/">Página Principal</a>
              </li>
              
              <li class="nav-item">
                <a class="nav-link" href="/comprimidos">Comprimidos</a>
<li class="nav-item">
                <a class="nav-link" href="/receitas">Receitas</a>
              </li>
              </li>
              
              <li class="nav-item">
                <a class="nav-link" href="/about">Sobre Nós</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/contact">Contacto</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#"><img src="images/search-icon.png"></a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="#">Conta</a>
              </li>          
            </ul>
          </div>
        </nav>
        <nav class="mobile_header navbar navbar-expand-lg navbar-light bg-light">
          <div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent2" aria-controls="navbarSupportedContent2" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent2">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item">
                <a class="nav-link" href="/">Home</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/about">About</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="doctor.html">Doctor</a>
              </li>
              <li class="nav-item">
                <a class="logo_main" href="index.html"><img src="images/logo.png"></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="depatments.html">Depatments</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="blog.html">Blog</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="/contact">Contact</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#"><img src="images/search-icon.png"></a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="#">LOGIN</a>
              </li>              
            </ul>
          </div>
        </nav>
      </div>
      <!-- header section end -->
      <!-- services section start -->
      <div class="services_section layout_padding padding_bottom_0">
        <div class="container">
          <h1 class="blog_text">Receitas</h1>
          <table class="table">
        <thead>
            <tr>
                <th>Nome da Receita</th>
                <th>Nº De Utente</th>
                <th>Forma Farmaceutica</th>
                <th>Dosagem</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
            @foreach ($data as $item)
            <tr>
                <td>{{ $item['nome_receita'] }}</td>
                <td>{{ $item['n_utente'] }}</td>
                <td>{{ $item['forma_farmaceutica'] }}</td>
                <td>{{ $item['dosagem'] }}</td>
                <td>{{ $item['data'] }}</td>
            </tr>
            @endforeach
        </tbody>
    </table>
        </div>
      </div>
      <!-- services section end -->
      <!-- footer section start -->
      <!-- info section -->
      <div class="info_section layout_padding">
        <div class="container ">
          <div class="info_content">
                <div>
            <div class="row">
              <div class="col-md-4">
                <br>
                <br>
                <br>
                <div class="d-flex">
                  <h5 width="2000">
                    YourPills
                  </h5>
                </div>
              </div>
              <div class="col-md-4">
                <div class="d-flex">
                  <h5>
                    Companhias
                  </h5>
                </div>
                <div class="d-flex ">
                  <ul>
                    <li>
                      <a href="https://www.poch.portugal2020.pt/pt-pt/Paginas/default.aspx">
                        POCH
                      </a>
                    </li>
                    <li>
                      <a href="https://filipa-vilhena.edu.pt">
                        ESFV
                      </a>
                    </li>
                    <li>
                      <a href="http://tgpsi.filipa-vilhena.edu.pt">
                        Curso TGPSI
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="col-md-4">
                <div class="d-flex">
                  <h5>
                    Sobre Nós
                  </h5>
                </div>
                <div class="d-flex ">
                  <ul>
                    <li>
                      <a href="Comprimidos.html">
                        Comprimidos
                      </a>
                    </li>
                    <li>
                      <a href="Receitas.html">
                        Receitas
                      </a>
                    </li>
                    <li>
                      <a href="Rotinas.html">
                        Rotinas
                      </a>
                    </li>
                    <li>
                      <a href="contact.html">
                        Entre em Contacto connosco
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          </div>
        </div>
      </div>
      <!-- end info section -->
      <!-- footer section end -->
      <!-- copyright section start -->
      <div class="copyright_section">
        <div class="container">
          <p class="copyright"><img src="images/poch.png" width="500"></a></p>
        </div>
      </div>
      <!-- copyright section end -->
        <!-- Javascript files-->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/jquery-3.0.0.min.js"></script>
        <script src="js/plugin.js"></script>
        <!-- sidebar -->
        <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
        <script src="js/custom.js"></script>
        <!-- javascript --> 
        <script src="js/owl.carousel.js"></script>
        <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
     </body>
     </html>