<template>
    <manage-badges></manage-badges>
</template>
<script>

    import ManageBadges from './badge/ManageBadges'

    export default {
        components: {
            ManageBadges
        }
    }
</script>

<style>



     .UIToolbarContainerDark a.dropdown-toggle {
    color: #fff;
      text-decoration: none;
}
 .UIToolbarContainerDark a.dropdown-toggle:hover, .UIToolbarContainerDark a.dropdown-toggle:focus{
      text-decoration: none;
       color: #fff;
}
     
     a{
         text-decoration: none;
         background-color: transparent;
         -webkit-text-decoration-skip: objects;
     }
    .alert{
develop
        max-width: 98%;
        margin: 0 auto;
        top: 7px;
    }

    .alert-dismissible .close {
        padding: 0.5rem 1.25rem !important;
    }

    .alert .close {
        line-height: 16px;
    }

    .btn-primary:hover {
        background-color: #476a9c;
    }

    .dropdown-toggle:after {
        display: none;
    }

    .dropdown-menu {
        font-size: inherit;
    }
</style>